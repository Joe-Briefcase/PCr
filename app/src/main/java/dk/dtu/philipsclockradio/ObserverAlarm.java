package dk.dtu.philipsclockradio;

public class ObserverAlarm extends Observer{

    public ObserverAlarm(StateStandby subject, ContextClockradio context){
        this.subject = subject;
        this.context = context;

    }

    @Override
    public void update(){

        System.out.println(subject.getTime().toString());
        System.out.println(context.singletonAlarm.getAL1().toString());

        if (subject.getTime().compareTo(context.singletonAlarm.getAL1()) == 0){
            subject.triggerAL1();
        }

        if (subject.getTime().compareTo(context.singletonAlarm.getAL2()) == 0){
            subject.triggerAL2();
        }
    }

}
