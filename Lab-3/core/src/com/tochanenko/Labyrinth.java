package com.tochanenko;

public class Labyrinth {
    private int [][] matrix;

    private int endX, endY;

    Labyrinth(int xLength, int yLength) {
        LabyrinthGenerator generator = new LabyrinthGenerator();
        generator.generate(xLength, yLength);
        this.matrix = generator.getMatrix();
        endX = generator.getXExit();
        endY = generator.getYExit();
        System.out.println("EXIT AT : " + endX + " " + endY);
    }

    public boolean canMove(int posX, int posY, Moves.Move direction) {
        if (direction == Moves.Move.UP)
            return (posY < (matrix.length - 1) && matrix[posY + 1][posX] != 1);
        if (direction == Moves.Move.RIGHT)
            return (posX < (matrix[0].length - 1) && matrix[posY][posX + 1] != 1);
        if (direction == Moves.Move.LEFT)
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
}
