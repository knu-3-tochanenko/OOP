package Entities;

public class VisualParameters {
    private Color stemColor;
    private Color leafColor;
    private float averageSize;

    VisualParameters(Color stemColor, Color leafColor, float averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public Color getStemColor() { return stemColor; }
    public Color getLeafColor() { return leafColor; }
    public float getAverageSize() { return averageSize; }
}
