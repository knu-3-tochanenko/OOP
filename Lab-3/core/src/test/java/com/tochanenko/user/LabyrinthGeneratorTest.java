package com.tochanenko.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabyrinthGeneratorTest {

    LabyrinthGenerator generator;
    LabyrinthGenerator generatorSeeded;
    LabyrinthGenerator generatorSeeded2;

    int x = 10;
    int y = 10;

    {
        generator = new LabyrinthGenerator(x, y);
        generatorSeeded = new LabyrinthGenerator(x, y, 1000);
        generatorSeeded2 = new LabyrinthGenerator(x, y, 2000);
    }

    @Test
    void getMatrix() {
        int[][] matrix = generator.getMatrix();
        assertEquals(matrix.length, 10);
        assertEquals(matrix[0].length, 10);
    }

    @Test
    void getXExit() {
        assertTrue(generator.getXExit() <= 10 && generator.getXExit() >= 0,
                "X exit");
        assertEquals(generatorSeeded.getXExit(), 4);
        assertEquals(generatorSeeded2.getXExit(), 0);
    }

    @Test
    void getYExit() {
        assertTrue(generator.getYExit() <= 10 && generator.getYExit() >= 0,
                "Y exit");
        assertEquals(generatorSeeded.getYExit(), 0);
        assertEquals(generatorSeeded2.getYExit(), 3);
    }
}