package dk.dtu.philipsclockradio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StateAL2 extends StateAdapter{

    private Date date;
    private SimpleDateFormat time;
    private Calendar cal = Calendar.getInstance();
    private int hour;
    private int min;

    @Override
    public void onEnterState(ContextClockradio context) {
        time = new SimpleDateFormat("HH:mm");
        date = new Date();
        date = context.singletonAlarm.getAL2();
        cal.setTime(date);
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        context.ui.setDisplayText(time.format(date));
    }

    @Override
    public void onClick_AL2(ContextClockradio context) {
        context.singletonAlarm.setAL2(date);
        context.ui.turnOnLED(5);
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        hour++;
        if (hour > 23)
            hour = 0;

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        date = cal.getTime();
        context.ui.setDisplayText(time.format(date));
    }

    @Override
    public void onClick_Min(ContextClockradio context) {
        min++;
        if (min > 59)
            min = 0;

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        date = cal.getTime();
        context.ui.setDisplayText(time.format(date));
    }
}
