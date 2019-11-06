package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class SingletonAlarm {

    private Date AL1;
    private int AL1state = 0;
    private Date AL2;
    private int AL2state = 0;
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
        AL1state = 1;
    }

    public void setAL2(Date date){
        AL2 = date;
        AL1state = 1;
    }

    public Date getAL1(){
        return AL1;
    }

    public Date getAL2(){
        return AL2;
    }

    public int getAl1State(){return AL1state;}

    public int getAl2State(){return AL2state;}

    public void setAL1state(int i){AL1state = i;}

    public void setAL2state(int i){AL2state = i;}
}
