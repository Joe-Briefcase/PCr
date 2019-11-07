package dk.dtu.philipsclockradio;

import android.os.Handler;

import java.util.Calendar;
import java.util.Date;

public class StateStandby extends StateAdapter {

    private Date mTime;
    private static Handler mHandler = new Handler();
    private ContextClockradio mContext;
    private Observer observer;
    private boolean AL1running = false;
    private boolean AL2running = false;
    private Calendar cal = Calendar.getInstance();

    StateStandby(Date time){
        mTime = time;
        observer = new ObserverAlarm(this, mContext);
    }

    //Opdaterer hvert 60. sekund med + 1 min til tiden
    Runnable mSetTime = new Runnable() {

        @Override
        public void run() {
            try {
                long currentTime = mTime.getTime();
                mTime.setTime(currentTime + 60000);
                mContext.setTime(mTime);
                notifyObserver();
            } finally {
                mHandler.postDelayed(mSetTime, 60000);
            }
        }
    };

    void startClock() {
        mSetTime.run();
        mContext.isClockRunning = true;
    }

    void stopClock() {
        mHandler.removeCallbacks(mSetTime);
        mContext.isClockRunning = false;
    }

    public void notifyObserver(){
        observer.update();
    }

    public Date getTime(){
        return mTime;
    }

    public void triggerAL1(){

        if (mContext.singletonAlarm.getAl1State() == 1) {
            AL1running = true;
            mContext.ui.turnOnTextBlink();
        } else if (mContext.singletonAlarm.getAl1State() == 2){
            AL1running = true;
            mContext.ui.turnOnTextBlink();
            mContext.ui.toggleRadioPlaying();
        }
    }

    public void triggerAL2(){
        if (mContext.singletonAlarm.getAl2State() == 1) {
            AL1running = true;
            mContext.ui.turnOnTextBlink();
        } else if (mContext.singletonAlarm.getAl2State() == 2){
            AL1running = true;
            mContext.ui.turnOnTextBlink();
            mContext.ui.toggleRadioPlaying();
        }
    }

    public void resetAlarm(){
        mContext.ui.turnOffTextBlink();
        if (mContext.ui.isRadioPlaying())
            mContext.ui.toggleRadioPlaying();
    }

    @Override
    public void onClick_Snooze(ContextClockradio context) {
        resetAlarm();

        if (AL1running == true) {
            AL1running = false;
            Date date = context.singletonAlarm.getAL1();
            cal.setTime(date);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, min + 9);
            date = cal.getTime();
            context.singletonAlarm.setAL1(date);
            System.out.println(date.toString());
        } else if (AL2running == true){
            AL2running = false;
            long time = context.singletonAlarm.getAL2().getTime();
            Date date = new Date();
            date.setTime(time + (60000 * 9));
            context.singletonAlarm.setAL2(date);
        }
    }

    @Override
    public void onEnterState(ContextClockradio context) {
        //Lokal context oprettet for at Runnable kan få adgang
        mContext = context;

        context.updateDisplayTime();
        if(!context.isClockRunning){
            startClock();
        }
    }

    // Skifter til set time mode.
    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        stopClock();
        context.setState(new StateSetTime());
    }

    // Skifter til radio mode.
    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateRadio());
    }

    @Override
    public void onClick_Sleep(ContextClockradio context) {
        context.setState(new StateSleep(mTime));
    }

    @Override
    public void onLongClick_AL1(ContextClockradio context) {
        context.setState(new StateAL1());
    }

    @Override
    public void onLongClick_AL2(ContextClockradio context) {
        context.setState(new StateAL2());
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {

        if (context.singletonAlarm.getAl1State() == 0){
            context.ui.turnOnLED(2);
            context.singletonAlarm.setAL1state(1);
        } else if (context.singletonAlarm.getAl1State() == 1){
            context.ui.turnOffLED(2);
            context.ui.turnOnLED(1);
            context.singletonAlarm.setAL1state(2);
        } else if (context.singletonAlarm.getAl1State() == 2){
            context.ui.turnOffLED(1);
            context.singletonAlarm.setAL1state(0);
        }
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        if (context.singletonAlarm.getAl2State() == 0){
            context.ui.turnOnLED(5);
            context.singletonAlarm.setAL2state(1);
        } else if (context.singletonAlarm.getAl2State() == 1){
            context.ui.turnOffLED(5);
            context.ui.turnOnLED(4);
            context.singletonAlarm.setAL2state(2);
        } else if (context.singletonAlarm.getAl2State() == 2){
            context.ui.turnOffLED(4);
            context.singletonAlarm.setAL2state(0);
        }
    }
}
