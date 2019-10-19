package com.tochanenko;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class LabyrinthGenerator {
    //  0 - Can move to
    //  1 - Wall
    // -1 - Not visited yet
    // -2 - Wall, but not permanent
    private int[][] matrix = null;
    private int xLength, yLength;
    private int xExit, yExit;
    Random random = new Random(System.currentTimeMillis());

    public enum MOVE {
        UP, LEFT, RIGHT, DOWN
    }

    private void printMatrix(int[][] matrix) {
        System.out.println("-----------------------\n");
        for (int i = yLength - 1; i >= 0; i--) {
            for (int j = 0; j < xLength; j++)
                if (matrix[i][j] >= 0)
                    System.out.print("  " + matrix[i][j]);
                else
                    System.out.print(" " + matrix[i][j]);
            System.out.println();
        }
    }

    public void generate(int xLength, int yLength) {
        matrix = new int[yLength][xLength];
        this.xLength = xLength;
        this.yLength = yLength;

        generateWalls();
        generatePasses();
        generateExit();
    }

    private void generateExit() {
        int[][] subMatrix = new int[yLength][xLength];
        for (int i = 0; i < yLength; i++)
            for (int j = 0; j < xLength; j++)
                subMatrix[i][j] = matrix[i][j];
        subMatrix[0][0] = subMatrix[1][0] = subMatrix[0][1] = 1;
        dfsPass(1, 1, subMatrix);

        int maximumValue = subMatrix[1][1];
        for (int i = 1; i < yLength - 1; i++) {
            if (subMatrix[i][1] > maximumValue) {
                maximumValue = subMatrix[i][1];
                yExit = i;
                xExit = 0;
//                System.out.println("1 END SET TO " + xExit + " " + yExit);
            }
            if (subMatrix[i][xLength - 2] > maximumValue) {
                maximumValue = subMatrix[i][xLength - 2];
                yExit = i;
                xExit = xLength - 1;
//                System.out.println("2 END SET TO " + xExit + " " + yExit);
            }
        }

        for (int i = 1; i < xLength - 1; i++) {
            if (subMatrix[1][i] > maximumValue) {
                maximumValue = subMatrix[1][i];
                yExit = 0;
                xExit = i;
//                System.out.println("3 END SET TO " + xExit + " " + yExit);
            }
            if (subMatrix[yLength - 2][i] > maximumValue) {
                maximumValue = subMatrix[yLength - 2][i];
                yExit = yLength - 1;
                xExit = i;
//                System.out.println("4 END SET TO " + xExit + " " + yExit);
            }
        }

        matrix[yExit][xExit] = 0;

    }

    private void dfsPass(int x, int y, int[][] subMatrix) {
        Queue<Triple> queue = new LinkedList<>();
        queue.add(new Triple(1, 1, 100));

        ArrayList<MOVE> availableMoves;
        while (!queue.isEmpty()) {
            Triple cur = queue.remove();
            availableMoves = getReadyPasses(cur.a, cur.b, subMatrix);
            subMatrix[cur.b][cur.a] = cur.c;

            if (availableMoves.contains(MOVE.UP))
                queue.add(new Triple(cur.a, cur.b + 1, cur.c + 1));
            if (availableMoves.contains(MOVE.RIGHT))
                queue.add(new Triple(cur.a + 1, cur.b, cur.c + 1));
            if (availableMoves.contains(MOVE.LEFT))
                queue.add(new Triple(cur.a - 1, cur.b, cur.c + 1));
            if (availableMoves.contains(MOVE.DOWN))
                queue.add(new Triple(cur.a, cur.b - 1, cur.c + 1));
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getXExit() {
        return this.xExit;
    }

    public int getYExit() {
        return this.yExit;
    }

    private ArrayList<MOVE> getPossiblePasses(int x, int y) {
        ArrayList<MOVE> res = new ArrayList<>();
        if (matrix[y - 1][x] == -1)
            res.add(MOVE.DOWN);
        if (matrix[y + 1][x] == -1)
            res.add(MOVE.UP);
        if (matrix[y][x - 1] == -1)
            res.add(MOVE.LEFT);
        if (matrix[y][x + 1] == -1)
            res.add(MOVE.RIGHT);
        return res;
    }

    private ArrayList<MOVE> getTemporaryWalls(int x, int y) {
        ArrayList<MOVE> res = new ArrayList<>();
        if (matrix[y - 1][x] == -2)
            res.add(MOVE.DOWN);
        if (matrix[y + 1][x] == -2)
            res.add(MOVE.UP);
        if (matrix[y][x - 1] == -2)
            res.add(MOVE.LEFT);
        if (matrix[y][x + 1] == -2)
            res.add(MOVE.RIGHT);
        return res;
    }

    private ArrayList<MOVE> getReadyPasses(int x, int y, int[][] subMatrix) {
        ArrayList<MOVE> res = new ArrayList<>();
//        printMatrix(subMatrix);
//        System.out.println("AV PASS: " + x + " " + y);
        if (subMatrix[y - 1][x] == 0)
            res.add(MOVE.DOWN);
        if (subMatrix[y + 1][x] == 0)
            res.add(MOVE.UP);
        if (subMatrix[y][x - 1] == 0)
            res.add(MOVE.LEFT);
        if (subMatrix[y][x + 1] == 0)
            res.add(MOVE.RIGHT);
        return res;
    }

    private void placeTemporaryWalls(int x, int y) {
//        System.out.println("Placing walls for " + x + " --- " + y);
        if (matrix[y - 1][x] < 0) {
            matrix[y - 1][x] = -2;
//            System.out.println("Wall down placed");
        }
        if (matrix[y + 1][x] < 0) {
            matrix[y + 1][x] = -2;
//        System.out.println("Wall up placed");
    }
        if (matrix[y][x - 1] < 0) {
            matrix[y][x - 1] = -2;
//            System.out.println("Wall left placed");
}
        if (matrix[y][x + 1] < 0) {
            matrix[y][x + 1] = -2;
//        System.out.println("Wall right placed");
        }
    }

    private void replaceWalls() {
        for (int i = 1; i < yLength - 1; i++)
            for (int j = 1; j < xLength - 1; j++)
                if (matrix[i][j] == -2)
                    matrix[i][j] = 1;
    }

    ArrayList<Integer> getRandomPass(ArrayList<MOVE> passes, int x, int y) {
//        System.out.println("Gets random path");
        ArrayList<Integer> res = new ArrayList<>();
        if (!passes.isEmpty()) {
            int rand = random.nextInt(passes.size());
            int value = rand % passes.size();
//            System.out.print("Got " + value + " = " + rand + " % " + passes.size());
            MOVE pass = passes.get(value);
//            System.out.println(" = " + pass);
            if (pass == MOVE.UP) {
                res.add(x);
                res.add(y + 1);
            }
            if (pass == MOVE.DOWN) {
                res.add(x);
                res.add(y - 1);
            }
            if (pass == MOVE.RIGHT) {
                res.add(x + 1);
                res.add(y);
            }
            if (pass == MOVE.LEFT) {
                res.add(x - 1);
                res.add(y);
            }
        }
//        System.out.println("Will return res");
        return res;
    }

    private void passCell(int x, int y) {
        matrix[y][x] = 0;
//        printMatrix();
//        System.out.println(x + " --- " + y);
        // Go to unvisited places
        ArrayList<MOVE> possiblePasses = getPossiblePasses(x, y);
        if (possiblePasses.size() > 0) {
//            System.out.println("Here");
            ArrayList<Integer> randomPass = getRandomPass(possiblePasses, x, y);
//            System.out.println("Before walls");
            placeTemporaryWalls(x, y);
//            System.out.println("After walls");
            passCell(randomPass.get(0), randomPass.get(1));
        }

        // If temporary wall has unvisited cells
        ArrayList<MOVE> possibleWalls = getTemporaryWalls(x, y);
        if (!possibleWalls.isEmpty()) {

            ArrayList<Integer> randomWall = getRandomPass(possibleWalls, x, y);
            ArrayList<MOVE> possiblePassesWall =
                    getPossiblePasses(randomWall.get(0), randomWall.get(1));

            if (!possiblePassesWall.isEmpty()) {
                ArrayList<Integer> randomWallPass =
                        getRandomPass(possiblePassesWall, randomWall.get(0), randomWall.get(1));
                placeTemporaryWalls(randomWall.get(0), randomWall.get(1));
                matrix[randomWall.get(1)][randomWall.get(0)] = 0;
                passCell(randomWallPass.get(0), randomWallPass.get(1));
            }
        }

    }

    private void generatePasses() {
        passCell(1, 1);
        replaceWalls();
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
        matrix[0][1] = matrix[1][0] = matrix[0][0] = 0;
    }
}
