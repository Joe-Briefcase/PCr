package dk.dtu.philipsclockradio;

import java.util.concurrent.ThreadLocalRandom;

public class StateRadio extends StateAdapter {

    // FM frekvenser.
    private float FMMinFreq = 87;
    private float FMMaxFreq = 108;
    private float FMCurrentFreq;

    // AM frekvenser.
    private int AMMinFreq = 530;
    private int AMMaxFreq = 1700;
    private int AMCurrentFreq;

    // true er FM og false er AM.
    private boolean radioMode = true;

    // Radio: on.
    @Override
    public void onEnterState(ContextClockradio context) {
        context.ui.toggleRadioPlaying();
        FMCurrentFreq = FMMinFreq;
        AMCurrentFreq = AMMinFreq;
        context.ui.setDisplayText(""+FMCurrentFreq);
    }
    // Radio: off.
    @Override
    public void onExitState(ContextClockradio context) {
        context.ui.toggleRadioPlaying();
    }

    // Skifter mellem FM og AM.
    @Override
    public void onClick_Power(ContextClockradio context) {
        // Skifter til AM
        if (radioMode == true) {
            radioMode = false;
            context.ui.setDisplayText(""+AMCurrentFreq);
        }
        // Skifter til FM
        else {
            radioMode = true;
            context.ui.setDisplayText(""+FMCurrentFreq);
        }
    }

    @Override
    public void onLongClick_Power(ContextClockradio context) {
        context.setState(new StateStandby(context.getTime()));
    }

    // Tuning -
    @Override
    public void onClick_Hour(ContextClockradio context) {

        // FM
        if (radioMode == true && FMCurrentFreq > FMMinFreq) {
            FMCurrentFreq = FMCurrentFreq - 0.1f;
            context.ui.setDisplayText(""+FMCurrentFreq);
        } // AM
        else if (radioMode == false && AMCurrentFreq > AMMinFreq) {
            AMCurrentFreq = AMCurrentFreq - 10;
            context.ui.setDisplayText(""+AMCurrentFreq);
        }

    }
    // Tuning +
    @Override
    public void onClick_Min(ContextClockradio context) {

        // FM
        if (radioMode == true && FMCurrentFreq < FMMaxFreq) {
            FMCurrentFreq = FMCurrentFreq + 0.1f;
            context.ui.setDisplayText(""+FMCurrentFreq);
        } // AM
        else if (radioMode == false && AMCurrentFreq < AMMaxFreq) {
            AMCurrentFreq = AMCurrentFreq + 10;
            context.ui.setDisplayText(""+AMCurrentFreq);
        }
    }

    // Auto-tune -
    @Override
    public void onLongClick_Hour(ContextClockradio context) {

        // FM
        if (radioMode == true && FMCurrentFreq > FMMinFreq) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 50); i++) {
                FMCurrentFreq = FMCurrentFreq - 0.1f;

                if (FMCurrentFreq == FMMinFreq) {
                    context.ui.setDisplayText("" + FMCurrentFreq);
                    break;
                }

                context.ui.setDisplayText("" + FMCurrentFreq);
            }
        } // AM
        else if (radioMode == false && AMCurrentFreq > AMMinFreq) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 50); i++) {
                AMCurrentFreq = AMCurrentFreq - 10;

                if (AMCurrentFreq == AMMinFreq) {
                    context.ui.setDisplayText("" + AMCurrentFreq);
                    break;
                }

                context.ui.setDisplayText("" + AMCurrentFreq);
            }
        }
    }

    // Auto-tune +
    @Override
    public void onLongClick_Min(ContextClockradio context) {

        // FM
        if (radioMode == true && FMCurrentFreq < FMMaxFreq) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 50); i++) {
                FMCurrentFreq = FMCurrentFreq + 0.1f;

                if (FMCurrentFreq == FMMaxFreq) {
                    context.ui.setDisplayText("" + FMCurrentFreq);
                    break;
                }

                context.ui.setDisplayText("" + FMCurrentFreq);
            }
        } // AM
        else if (radioMode == false && AMCurrentFreq < AMMaxFreq) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(5, 50); i++) {
                AMCurrentFreq = AMCurrentFreq + 10;

                if (AMCurrentFreq == AMMaxFreq) {
                    context.ui.setDisplayText("" + AMCurrentFreq);
                    break;
                }

                context.ui.setDisplayText("" + AMCurrentFreq);
            }
        }
    }
}
