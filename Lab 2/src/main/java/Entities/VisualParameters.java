package Entities;

import java.text.DecimalFormat;

public class VisualParameters {
    private Color stemColor;
    private Color leafColor;
    private float averageSize;

    VisualParameters(Color stemColor, Color leafColor, float averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public Color getStemColor() {
        return stemColor;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public float getAverageSize() {
        return averageSize;
    }

    public String toString() {
        return "\t\t\tStem color:\t" + stemColor.toString()
                + "\t\t\tLeaf Color:\t" + leafColor.toString()
                + "\t\t\tAverage size:\t"
                + new DecimalFormat("#,##0.00").format(averageSize)
                + '\n';
    }
}
