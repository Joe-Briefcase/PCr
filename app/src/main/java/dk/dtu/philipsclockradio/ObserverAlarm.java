package dk.dtu.philipsclockradio;

import java.util.Calendar;
import java.util.Date;

public class ObserverAlarm extends Observer{

    private Calendar cal = Calendar.getInstance();
    private Calendar newCal = Calendar.getInstance();
    private Date time;

    public ObserverAlarm(StateStandby subject, ContextClockradio context){
        this.subject = subject;
        this.context = context;

    }

//    @Override
//    public void update() {
//        cal.setTime(subject.getTime());
//
//        newCal.set(Calendar.HOUR, cal.get(Calendar.HOUR));
//        newCal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
//
//        time = newCal.getTime();
//
//        if (time.compareTo(context.singletonAlarm.getAL1()) == 0){
//            subject.triggerAL1();
//        } else if (time.compareTo(context.singletonAlarm.getAL2()) == 0){
//            subject.triggerAL2();
//        }
//    }

    @Override
    public void update(){

        System.out.println(subject.getTime().toString());
        System.out.println(context.singletonAlarm.getAL1().toString());

        if (subject.getTime().compareTo(context.singletonAlarm.getAL1()) == 0){
            subject.triggerAL1();
        }
    }

}
