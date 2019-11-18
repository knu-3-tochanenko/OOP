package Entities;

public enum Color {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    DARK_GREEN(51, 102, 0),
    PINK(255, 102, 178),
    PURPLE(153, 51, 255),
    MAGENTA(255, 0, 255);

    Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private int red;
    private int green;
    private int blue;

    public String toString() {
        return "R: " + red + " G: " + green + " B: " + blue;
    }
}
