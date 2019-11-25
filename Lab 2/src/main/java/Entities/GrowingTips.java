package Entities;

public class GrowingTips {
    private int temperature;
    private boolean needsLight;
    private int watering;       // Per week

    public GrowingTips(int temperature, boolean needsLight, int watering) {
        this.temperature = temperature;
        this.needsLight = needsLight;
        this.watering = watering;
    }

    public GrowingTips() {

    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public boolean isNeedsLight() {
        return needsLight;
    }

    public void setNeedsLight(boolean needsLight) {
        this.needsLight = needsLight;
    }

    public String toString() {
        return "\t\t\ttemperature:\t" + temperature
                + "\n\t\t\tneeds light:\t" + needsLight
                + "\n\t\t\twatering:\t" + watering + " (ml)\n";
    }
}
