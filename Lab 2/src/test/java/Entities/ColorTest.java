package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    private Color red = new Color(255, 0, 0);
    private Color green = new Color(0, 255, 0);
    private Color blue = new Color(0, 0, 255);
    private Color purple = new Color(255, 0, 255);

    @Test
    void getRed() {
        assertEquals(red.getRed(), 255);
        assertEquals(green.getRed(), 0);
        assertEquals(blue.getRed(), 0);
        assertEquals(purple.getRed(), 255);
    }

    @Test
    void getGreen() {
        assertEquals(red.getGreen(), 0);
        assertEquals(green.getGreen(), 255);
        assertEquals(blue.getGreen(), 0);
        assertEquals(purple.getGreen(), 0);
    }

    @Test
    void getBlue() {
        assertEquals(red.getBlue(), 0);
        assertEquals(green.getBlue(), 0);
        assertEquals(blue.getBlue(), 255);
        assertEquals(purple.getBlue(), 255);
    }

    @Test
    void testToString() {
        assertEquals(red.toString(), "R:255 G:0 B:0");
        assertEquals(green.toString(), "R:0 G:255 B:0");
        assertEquals(blue.toString(), "R:0 G:0 B:255");
        assertEquals(purple.toString(), "R:255 G:0 B:255");
    }
}