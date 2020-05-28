package com.tochanenko.user;

public class Labyrinth {
    private int[][] matrix;

    private int endX, endY;

    public Labyrinth(int[][] matrix, int endX, int endY) {
        int xLength = matrix[0].length;
        int yLength = matrix.length;
        this.matrix = new int[yLength][xLength];

        for (int i = 0; i < yLength; i++)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, xLength);

        this.endX = endX;
        this.endY = endY;
    }

    public boolean canMove(int posX, int posY, Move direction) {
        if (posX < 0 || posY < 0 || posX >= matrix[0].length || posY >= matrix.length)
            return false;
        if (direction == Move.UP)
            return (posY < (matrix.length - 1) && matrix[posY + 1][posX] != 1);
        if (direction == Move.RIGHT)
            return (posX < (matrix[0].length - 1) && matrix[posY][posX + 1] != 1);
        if (direction == Move.LEFT)
            return (posX > 0 && matrix[posY][posX - 1] != 1);
        else
            return (posY > 0 && matrix[posY - 1][posX] != 1);
    }

    public boolean isEnd(int posX, int posY) {
        return (posY == endY) && (posX == endX);
    }

    public boolean isWall(int posX, int posY) {
        return matrix[posY][posX] != 0;
    }

    public int getXLength() {
        return matrix[0].length;
    }

    public int getYLength() {
        return matrix.length;
    }

    // For tests
    int getEndX() {
        return endX;
    }

    int getEndY() {
        return endY;
    }
}
