package Entities;

public class Color {
    Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private int red;
    private int green;
    private int blue;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String toString() {
        return "R:" + red + " G:" + green + " B:" + blue;
    }
}
