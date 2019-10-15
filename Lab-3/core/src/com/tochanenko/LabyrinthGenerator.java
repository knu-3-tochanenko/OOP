package com.tochanenko;

import java.util.Random;

public class LabyrinthGenerator {
    //  0 - Can move to
    //  1 - Wall
    // -1 - Not visited yet
    // -2 - Wall, but not permanent
    private short[][] matrix = null;
    private int xLength, yLength;
    private int xExit, yExit;
    Random random = new Random(System.currentTimeMillis());

    public void generate(int xLength, int yLength) {
        matrix = new short[yLength][xLength];
        this.xLength = xLength;
        this.yLength = yLength;

        generateWalls();
        generatePasses(1, 1);
    }

    public short[][] getMatrix() {
        return matrix;
    }

    public int getXExit() {
        return this.xExit;
    }

    public int getYExit() {
        return this.yExit;
    }


    private void generatePasses(int x, int y) {

    }

    private void generateWalls() {
        for (int i = 0; i < yLength; i++)
            matrix[i][0] = matrix[i][xLength - 1] = 1;
        for (int i = 0; i < xLength; i++)
            matrix[0][i] = matrix[yLength - 1][i] = 1;

        // Fill labyrinth with unvisited tag
        for (int i = 1; i < yLength - 1; i++)
            for (int j = 1; j < xLength - 1; j++)
                matrix[i][j] = -1;

        // Let character to move into labyrinth
        //matrix[0][1] = matrix[1][0] = 0;
    }
}
