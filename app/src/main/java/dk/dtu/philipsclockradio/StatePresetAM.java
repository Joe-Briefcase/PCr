package dk.dtu.philipsclockradio;

public class StatePresetAM extends StateAdapter{

    private int presetNumber = 1;
    private int AMFreq;

    StatePresetAM(int PresetFreq){AMFreq = PresetFreq;}

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
        context.singletonPresets.setAMPreset(presetNumber, AMFreq);
        context.ui.turnOffTextBlink();
        context.setState(new StateRadio());
    }
}
