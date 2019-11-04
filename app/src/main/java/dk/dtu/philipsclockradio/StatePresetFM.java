package dk.dtu.philipsclockradio;

public class StatePresetFM extends StateAdapter{

    private int presetNumber = 1;
    private float FMFreq;

    StatePresetFM(float PresetFreq){FMFreq = PresetFreq;}

    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.turnOnTextBlink();
        context.ui.setDisplayText("" + presetNumber);
    }

    @Override
    public void onLongClick_Preset(ContextClockradio context) {
        presetNumber++;
        if (presetNumber == 21)
            presetNumber = 1;

        context.ui.setDisplayText("" + presetNumber);
    }

    @Override
    public void onClick_Preset(ContextClockradio context) {
        context.singletonPresets.setFMPreset(presetNumber, FMFreq);
        context.ui.turnOffTextBlink();
        context.setState(new StateRadio());
    }
}
