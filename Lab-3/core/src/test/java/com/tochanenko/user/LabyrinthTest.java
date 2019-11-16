package com.tochanenko.user;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {

    private Labyrinth labyrinth;
    private Labyrinth randomLabyrinth;

    private int x = 25;
    private int y = 25;

    {
        long SEED = 394785;
        labyrinth = new Labyrinth(x, y, SEED);
        randomLabyrinth = new Labyrinth(x, y);
    }

    @Test
    void canMove() {
        assertFalse(labyrinth.canMove(-1, -1, Move.UP));
        assertFalse(labyrinth.canMove(-1, -1, Move.DOWN));
        assertFalse(labyrinth.canMove(-1, -1, Move.LEFT));
        assertFalse(labyrinth.canMove(-1, -1, Move.RIGHT));

        assertFalse(labyrinth.canMove(-1, y, Move.UP));
        assertFalse(labyrinth.canMove(x, -1, Move.UP));

        assertFalse(labyrinth.canMove(x, y / 2, Move.UP));
        assertFalse(labyrinth.canMove(-1, y / 2, Move.UP));
        assertFalse(labyrinth.canMove(x / 2, y, Move.UP));
        assertFalse(labyrinth.canMove(x / 2, -1, Move.UP));

        assertFalse(labyrinth.canMove(x, y, Move.UP));
        assertFalse(labyrinth.canMove(x, y, Move.DOWN));
        assertFalse(labyrinth.canMove(x, y, Move.LEFT));
        assertFalse(labyrinth.canMove(x, y, Move.RIGHT));

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                assertThat(
                        labyrinth.canMove(i, j, Move.UP),
                        anyOf(is(true), is(false))
                );
                assertThat(
                        labyrinth.canMove(i, j, Move.RIGHT),
                        anyOf(is(true), is(false))
                );
                assertThat(
                        labyrinth.canMove(i, j, Move.DOWN),
                        anyOf(is(true), is(false))
                );
                assertThat(
                        labyrinth.canMove(i, j, Move.LEFT),
                        anyOf(is(true), is(false))
                );
            }
    }

    @Test
    void isEnd() {
        assertFalse(labyrinth.isEnd(x / 2, y / 2));
        int endX = labyrinth.getEndX();
        int endY = labyrinth.getEndY();
        assertTrue(labyrinth.isEnd(endX, endY));
        assertFalse(labyrinth.isEnd(x / 2, endY));
        assertFalse(labyrinth.isEnd(endX, x / 2));
    }

    @Test
    void isWall() {
        assertTrue(labyrinth.isWall(x - 1, y - 1));
        assertFalse(labyrinth.isWall(0, 0));
        assertThat(
                labyrinth.isWall(x / 2, y / 2),
                anyOf(is(true), is(false))
        );
    }

    @Test
    void getXLength() {
        assertEquals(labyrinth.getXLength(), x);
        assertEquals(randomLabyrinth.getXLength(), x);
    }

    @Test
    void getYLength() {
        assertEquals(labyrinth.getYLength(), y);
        assertEquals(labyrinth.getYLength(), y);
    }
}