package Entities;

import java.text.DecimalFormat;

public class VisualParameters {
    private String stemColor;
    private String leafColor;
    private float averageSize;

    public VisualParameters(String stemColor, String leafColor, float averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public VisualParameters() { }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public float getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(float averageSize) {
        this.averageSize = averageSize;
    }

    public String toString() {
        return "\u001b[36;1m\t\t\tstem color:\u001b[0m\t\t" + stemColor
                + "\u001b[36;1m\n\t\t\tleaf color:\u001b[0m\t\t" + leafColor
                + "\u001b[36;1m\n\t\t\taverage size:\u001b[0m\t"
                + new DecimalFormat("#,##0.00").format(averageSize);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VisualParameters)
            return ((((VisualParameters) obj).averageSize == this.averageSize)
            && (((VisualParameters) obj).leafColor.equals(this.leafColor))
            && (((VisualParameters) obj).stemColor.equals(this.stemColor)));
        return false;
    }
}
