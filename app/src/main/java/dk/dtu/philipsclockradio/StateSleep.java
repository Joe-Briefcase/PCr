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
    long idleTime = 5;

    StateSleep(Date time){
        this.mTime = time;
    }


    Runnable trackTime = new Runnable() {
        @Override
        public void run() {
            mContext.setState(new StateStandby(mTime));
        }
    };

    void startTime(){
        mHandler.postDelayed(trackTime, 5000);
    }

    void stopTime(){
        mHandler.removeCallbacks(trackTime);
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        mContext = context;
        context.ui.turnOnLED(3);
        context.ui.setDisplayText("" + sleepTimes[sleepTimesCounter]);
        startTime();
    }

    @Override
    public void onExitState(ContextClockradio context) {
        stopTime();
        context.ui.turnOffLED(3);
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        stopTime();
        sleepTimesCounter++;

        if (sleepTimesCounter == 6) {
            sleepTimesCounter = 0;
            context.ui.turnOnLED(3);
            context.ui.setDisplayText("" + sleepTimes[sleepTimesCounter]);
        }
        else if (sleepTimesCounter == 5) {
            context.ui.turnOffLED(3);
            context.ui.setDisplayText("OFF");
        } else
            context.ui.setDisplayText("" + sleepTimes[sleepTimesCounter]);

        startTime();
    }
}
