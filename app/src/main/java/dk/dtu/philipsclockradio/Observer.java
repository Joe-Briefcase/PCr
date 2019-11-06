package dk.dtu.philipsclockradio;

public abstract class Observer {
    protected StateStandby subject;
    protected ContextClockradio context;
    public abstract void update();
}
