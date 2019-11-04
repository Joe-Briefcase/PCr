package dk.dtu.philipsclockradio;

import java.util.Date;
import android.os.Handler;

public class StateSleep extends StateAdapter {

    private int[] sleepTimes = new int[]{120, 90, 60, 30, 15, 0};
    private int sleepTimesCounter = 0;
    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    long startTime;

    StateSleep(Date time){
        this.mTime = time;
    }

    Runnable trackTime = new Runnable() {
        @Override
        public void run() {
            try {
                if (mTime.getTime() - startTime == 0) {
                    mContext.setState(new StateStandby(mTime));
                }
                startTime = mTime.getTime();
            } finally {
                mHandler.postDelayed(trackTime, 5000);
            }
        }
    };

    void startTime(){
        trackTime.run();
    }

    void stopTime(){
        mHandler.removeCallbacks(trackTime);
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        mContext = context;
        context.ui.turnOnLED(3);
        context.ui.setDisplayText("" + sleepTimes[sleepTimesCounter]);

        //context.isclockrunning?
        startTime();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.turnOffLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        sleepTimesCounter++;

        if (sleepTimesCounter == 6)
            sleepTimesCounter = 0;
        else if (sleepTimesCounter == 5) {
            context.ui.turnOffLED(3);
            context.ui.setDisplayText("OFF");
        } else
            context.ui.setDisplayText("" + sleepTimes[sleepTimesCounter]);

    }
}
