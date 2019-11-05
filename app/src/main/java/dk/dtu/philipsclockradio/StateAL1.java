package dk.dtu.philipsclockradio;

public class StateAL1 extends StateAdapter{

    private int hour = 0;
    private int min = 0;

    @Override
    public void onEnterState(ContextClockradio context) {

    }

    @Override
    public void onExitState(ContextClockradio context) {

        // Tænd alarm LED for AL1.
    }

    @Override
    public void onClick_AL1(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    @Override
    public void onClick_Hour(ContextClockradio context) {
        hour++;
        if (hour < 25)
            hour = 0;
        context.ui.setDisplayText(hour + ":" + min);
    }

    @Override
    public void onClick_Min(ContextClockradio context) {

    }
}
