package dk.dtu.philipsclockradio;

public class SingletonPresets {

    // Arrays der indeholder presets.
    private float[] FMPresets = new float[20];
    private int[] AMPresets = new int[20];

    public void setFMPreset(int presetNumber, float presetFreq){
        FMPresets[presetNumber - 1] = presetFreq;
    }

    public void setAMPreset(int presetNumber, int presetFreq){
        AMPresets[presetNumber - 1] = presetFreq;
    }

    public float getFMPreset(int presetNumber){
        return FMPresets[presetNumber - 1];
    }

    public int getAMPreset(int presetNumber){
        return AMPresets[presetNumber - 1];
    }
}
