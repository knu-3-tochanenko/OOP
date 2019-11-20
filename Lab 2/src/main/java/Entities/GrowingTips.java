package Entities;

public class GrowingTips {
    private int temperature;
    private boolean needsLight;
    private int watering;       // Per week

    GrowingTips(int temperature, boolean needsLight, int watering) {
        this.temperature = temperature;
        this.needsLight = needsLight;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getWatering() {
        return watering;
    }

    public boolean isNeedsLight() {
        return needsLight;
    }

    public String toString() {
        return "\t\t\ttemperature:\t" + temperature
                + "\t\t\tneeds light:\t" + needsLight
                + "\t\t\twatering:\t" + watering + "( ml)\n";
    }
}
