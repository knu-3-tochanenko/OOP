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
        return "\t\t\tStem color:\t" + stemColor
                + "\t\t\tLeaf Color:\t" + leafColor
                + "\t\t\tAverage size:\t"
                + new DecimalFormat("#,##0.00").format(averageSize)
                + '\n';
    }
}
