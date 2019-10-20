package com.tochanenko;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthGeneratorTest {

    @org.junit.jupiter.api.Test
    void getMatrix() {
        LabyrinthGenerator generator = new LabyrinthGenerator();
        generator.generate(10, 10);
        int[][] matrix = generator.getMatrix();
        assertEquals(matrix.length, 10);
        assertEquals(matrix[0].length, 10);
    }

    @org.junit.jupiter.api.Test
    void getXExit() {
        LabyrinthGenerator generator = new LabyrinthGenerator();
        generator.generate(10, 10);
        assertTrue(generator.getXExit() <= 10 && generator.getXExit() >= 0,
                "X exit");
    }

    @org.junit.jupiter.api.Test
    void getYExit() {
        LabyrinthGenerator generator = new LabyrinthGenerator();
        generator.generate(10, 10);
        assertTrue(generator.getYExit() <= 10 && generator.getYExit() >= 0,
                "Y exit");
    }
}