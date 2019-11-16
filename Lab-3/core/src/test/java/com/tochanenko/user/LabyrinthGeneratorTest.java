package com.tochanenko.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LabyrinthGeneratorTest {

    LabyrinthGenerator generatorSeeded0;
    LabyrinthGenerator generatorSeeded1;
    LabyrinthGenerator generatorSeeded2;
    LabyrinthGenerator generatorSeeded3;
    LabyrinthGenerator generatorSeeded4;

    int x = 10;
    int y = 10;

    {
        generatorSeeded0 = new LabyrinthGenerator(x, y, 5555);
        generatorSeeded1 = new LabyrinthGenerator(x, y, 1000);
        generatorSeeded2 = new LabyrinthGenerator(x, y, 2000);
        generatorSeeded3 = new LabyrinthGenerator(x * 2, y * 2, 9999);
        generatorSeeded4 = new LabyrinthGenerator(x * 2, y * 2, 9);

        System.out.println(generatorSeeded4.getXExit() + " - " + generatorSeeded4.getYExit());
    }

    @Test
    void getMatrix() {
        int[][] matrix = generatorSeeded0.getMatrix();
        assertEquals(matrix.length, 10);
        assertEquals(matrix[0].length, 10);
    }

    @Test
    void getXExit() {
        assertEquals(generatorSeeded0.getXExit(), 9);
        assertEquals(generatorSeeded1.getXExit(), 4);
        assertEquals(generatorSeeded2.getXExit(), 0);
        assertEquals(generatorSeeded3.getXExit(), 6);
        assertEquals(generatorSeeded4.getXExit(), 19);
    }

    @Test
    void getYExit() {
        assertEquals(generatorSeeded0.getYExit(), 1);
        assertEquals(generatorSeeded1.getYExit(), 0);
        assertEquals(generatorSeeded2.getYExit(), 3);
        assertEquals(generatorSeeded3.getYExit(), 19);
        assertEquals(generatorSeeded4.getYExit(), 3);
    }
}