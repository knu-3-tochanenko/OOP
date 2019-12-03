package com.tochanenko.user;

import java.util.ArrayList;
import java.util.List;
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
    private long seed = System.currentTimeMillis();
    private Random random;

    public Labyrinth generate(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        generateValues();
        return new Labyrinth(matrix, getXExit(), getYExit());
    }

    public Labyrinth generate(int xLength, int yLength, long seed) {
        this.seed = seed;
        this.xLength = xLength;
        this.yLength = yLength;
        generateValues();
        return new Labyrinth(matrix, getXExit(), getYExit());
    }

    private void generateValues() {
        random = new Random(seed);
        matrix = new int[yLength][xLength];

        generateWalls();
        generatePasses();
        generateExit();
    }

    int[][] getMatrix() {
        return matrix;
    }

    int getXExit() {
        return this.xExit;
    }

    int getYExit() {
        return this.yExit;
    }

    private void generateExit() {
        int[][] subMatrix = new int[yLength][xLength];
        for (int i = 0; i < yLength; i++)
            if (xLength >= 0) System.arraycopy(matrix[i], 0, subMatrix[i], 0, xLength);
        subMatrix[0][0] = subMatrix[1][0] = subMatrix[0][1] = 1;
        dfsPass(subMatrix);

        int maximumValue = subMatrix[1][1];
        for (int i = 1; i < yLength - 1; i++) {
            if (subMatrix[i][1] > maximumValue) {
                maximumValue = subMatrix[i][1];
                yExit = i;
                xExit = 0;
            }
            if (subMatrix[i][xLength - 2] > maximumValue) {
                maximumValue = subMatrix[i][xLength - 2];
                yExit = i;
                xExit = xLength - 1;
            }
        }

        for (int i = 1; i < xLength - 1; i++) {
            if (subMatrix[1][i] > maximumValue) {
                maximumValue = subMatrix[1][i];
                yExit = 0;
                xExit = i;
            }
            if (subMatrix[yLength - 2][i] > maximumValue) {
                maximumValue = subMatrix[yLength - 2][i];
                yExit = yLength - 1;
                xExit = i;
            }
        }

        matrix[yExit][xExit] = 0;

    }

    private void dfsPass(int[][] subMatrix) {
        Queue<Triple> queue = new LinkedList<>();
        queue.add(new Triple(1, 1, 100));

        List<Move> availableMoves;
        while (!queue.isEmpty()) {
            Triple cur = queue.remove();
            availableMoves = getReadyPasses(cur.a, cur.b, subMatrix);
            subMatrix[cur.b][cur.a] = cur.c;

            if (availableMoves.contains(Move.UP))
                queue.add(new Triple(cur.a, cur.b + 1, cur.c + 1));
            if (availableMoves.contains(Move.RIGHT))
                queue.add(new Triple(cur.a + 1, cur.b, cur.c + 1));
            if (availableMoves.contains(Move.LEFT))
                queue.add(new Triple(cur.a - 1, cur.b, cur.c + 1));
            if (availableMoves.contains(Move.DOWN))
                queue.add(new Triple(cur.a, cur.b - 1, cur.c + 1));
        }
    }

    private List<Move> getPassesTemplate(int x, int y, int equals, int[][] matrix) {
        List<Move> res = new ArrayList<>();
        if (matrix[y - 1][x] == equals)
            res.add(Move.DOWN);
        if (matrix[y + 1][x] == equals)
            res.add(Move.UP);
        if (matrix[y][x - 1] == equals)
            res.add(Move.LEFT);
        if (matrix[y][x + 1] == equals)
            res.add(Move.RIGHT);
        return res;
    }


    private List<Move> getPossiblePasses(int x, int y) {
        return getPassesTemplate(x, y, -1, this.matrix);
    }

    private List<Move> getTemporaryWalls(int x, int y) {
        return getPassesTemplate(x, y, -2, this.matrix);
    }

    private List<Move> getReadyPasses(int x, int y, int[][] subMatrix) {
        return getPassesTemplate(x, y, 0, subMatrix);
    }

    private void placeTemporaryWalls(int x, int y) {
        if (matrix[y - 1][x] < 0) {
            matrix[y - 1][x] = -2;
        }
        if (matrix[y + 1][x] < 0) {
            matrix[y + 1][x] = -2;
        }
        if (matrix[y][x - 1] < 0) {
            matrix[y][x - 1] = -2;
        }
        if (matrix[y][x + 1] < 0) {
            matrix[y][x + 1] = -2;
        }
    }

    private void replaceWalls() {
        for (int i = 1; i < yLength - 1; i++)
            for (int j = 1; j < xLength - 1; j++)
                if (matrix[i][j] == -2)
                    matrix[i][j] = 1;
    }

    private List<Integer> getRandomPass(List<Move> passes, int x, int y) {
        List<Integer> res = new ArrayList<>();
        int rand = random.nextInt(passes.size());
        int value = rand % passes.size();
        Move pass = passes.get(value);
        if (pass == Move.UP) {
            res.add(x);
            res.add(y + 1);
        }
        if (pass == Move.DOWN) {
            res.add(x);
            res.add(y - 1);
        }
        if (pass == Move.RIGHT) {
            res.add(x + 1);
            res.add(y);
        }
        if (pass == Move.LEFT) {
            res.add(x - 1);
            res.add(y);
        }
        return res;
    }

    private void passCell(int x, int y) {
        matrix[y][x] = 0;
        List<Move> possiblePasses = getPossiblePasses(x, y);
        if (possiblePasses.size() > 0) {
            List<Integer> randomPass = getRandomPass(possiblePasses, x, y);
            placeTemporaryWalls(x, y);
            passCell(randomPass.get(0), randomPass.get(1));
        }

        // If temporary wall has unvisited cells
        List<Move> possibleWalls = getTemporaryWalls(x, y);
        if (!possibleWalls.isEmpty()) {

            List<Integer> randomWall = getRandomPass(possibleWalls, x, y);
            List<Move> possiblePassesWall =
                    getPossiblePasses(randomWall.get(0), randomWall.get(1));

            if (!possiblePassesWall.isEmpty()) {
                List<Integer> randomWallPass =
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
