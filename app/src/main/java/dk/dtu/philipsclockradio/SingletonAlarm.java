package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class SingletonAlarm {

    private Date AL1;
    private Date AL2;
    private Calendar cal = Calendar.getInstance();

    public SingletonAlarm(){
        AL1 = new Date();
        AL2 = new Date();

        cal.set(2019, 1, 1, 12, 00);

        AL1 = cal.getTime();
        AL2 = cal.getTime();
    }

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
