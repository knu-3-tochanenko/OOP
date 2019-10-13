package com.tochanenko;

public class Labyrinth {
    private short [][] matrix = {
            {0, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
    };

    private short endX = 6, endY = 0;

    public enum MOVE {
        UP, LEFT, RIGHT, DOWN
    }
    public boolean canMove(int posX, int posY, MOVE direction) {
        if (direction == MOVE.UP)
            return (posY < (matrix.length - 1) && matrix[posY + 1][posX] != 1);
        if (direction == MOVE.RIGHT)
            return (posX < (matrix[0].length - 1) && matrix[posY][posX + 1] != 1);
        if (direction == MOVE.LEFT)
            return (posX > 0 && matrix[posY][posX - 1] != 1);
        else
            return (posY > 0 && matrix[posY - 1][posX] != 1);
    }

    public boolean isEnd(int posX, int posY) {
        return (posY == endY && posX == endX);
    }

    public boolean isWall(int posX, int posY) {
        return matrix[posY][posX] == 1;
    }

    public int getXLength() {
        return matrix[0].length;
    }

    public int getYLength() {
        return matrix.length;
    }
}
