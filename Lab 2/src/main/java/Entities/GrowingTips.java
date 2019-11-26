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
        return "\u001b[36;1m\t\t\ttemperature:\u001b[0m\t" + temperature
                + "\u001b[36;1m\n\t\t\tneeds light:\u001b[0m\t" + needsLight
                + "\u001b[36;1m\n\t\t\twatering:\u001b[0m\t\t" + watering + " (ml)\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GrowingTips)
            return ((((GrowingTips) obj).watering == this.watering)
            && (((GrowingTips) obj).isNeedsLight() == this.isNeedsLight())
            && (((GrowingTips) obj).temperature == this.temperature));
        return false;
    }
}
