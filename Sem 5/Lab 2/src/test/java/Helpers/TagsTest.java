package Helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagsTest {
    @Test
    void noneTest() {
        assertEquals(Tags.NONE.toString(), "none");
    }

    @Test
    void plantTest() {
        assertEquals(Tags.PLANT.toString(), "plant");
    }

    @Test
    void nameTest() {
        assertEquals(Tags.NAME.toString(), "name");
    }

    @Test
    void soilTest() {
        assertEquals(Tags.SOIL.toString(), "soil");
    }

    @Test
    void originTest() {
        assertEquals(Tags.ORIGIN.toString(), "origin");
    }

    @Test
    void visualParametersTest() {
        assertEquals(Tags.VISUAL_PARAMETERS.toString(), "visual-parameters");
    }

    @Test
    void stemColorTest() {
        assertEquals(Tags.STEM_COLOR.toString(), "stem-color");
    }

    @Test
    void leafColorTest() {
        assertEquals(Tags.LEAF_COLOR.toString(), "leaf-color");
    }

    @Test
    void averageSizeTest() {
        assertEquals(Tags.AVERAGE_SIZE.toString(), "average-size");
    }

    @Test
    void growingTipsTest() {
        assertEquals(Tags.GROWING_TIPS.toString(), "growing-tips");
    }

    @Test
    void temperatureTest() {
        assertEquals(Tags.TEMPERATURE.toString(), "temperature");
    }

    @Test
    void needsLightTest() {
        assertEquals(Tags.NEEDS_LIGHT.toString(), "needs-light");
    }

    @Test
    void wateringTest() {
        assertEquals(Tags.WATERING.toString(), "watering");
    }

    @Test
    void breedingTest() {
        assertEquals(Tags.BREEDING.toString(), "breeding");
    }
}