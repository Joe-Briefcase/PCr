package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class ObserverAlarm {

    private Date AL1;
    private Date AL2;
    private Calendar cal = Calendar.getInstance();

    public ObserverAlarm(){
        AL1 = new Date();
        AL2 = new Date();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        AL1 = cal.getTime();
        AL2 = cal.getTime();
    }

    //update - alarm trigger

    //notify fra standby

    public void setAL1(Date date){
        AL1 = date;
    }

    public void setAL2(Date date){
        AL2 = date;
    }

    public Date getAL1(){
        return AL1;
    }

    public Date getAL2(){
        return AL2;
    }
}
