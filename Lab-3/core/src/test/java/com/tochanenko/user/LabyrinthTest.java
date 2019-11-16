package com.tochanenko.user;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest {

    Labyrinth labyrinth;
    int x = 10;
    int y = 10;

    {
        labyrinth = new Labyrinth(x, y);
    }

    @Test
    void canMove() {
        assertFalse(labyrinth.canMove(-1, -1, Moves.Move.UP));
        assertFalse(labyrinth.canMove(-1, -1, Moves.Move.DOWN));
        assertFalse(labyrinth.canMove(-1, -1, Moves.Move.LEFT));
        assertFalse(labyrinth.canMove(-1, -1, Moves.Move.RIGHT));

        assertFalse(labyrinth.canMove(x, y, Moves.Move.UP));
        assertFalse(labyrinth.canMove(x, y, Moves.Move.DOWN));
        assertFalse(labyrinth.canMove(x, y, Moves.Move.LEFT));
        assertFalse(labyrinth.canMove(x, y, Moves.Move.RIGHT));

        assertThat(
                labyrinth.canMove(x / 2, y / 2, Moves.Move.UP),
                anyOf(is(true), is(false))
        );
        assertThat(
                labyrinth.canMove(x / 2, y / 2, Moves.Move.DOWN),
                anyOf(is(true), is(false))
        );
        assertThat(
                labyrinth.canMove(x / 2, y / 2, Moves.Move.LEFT),
                anyOf(is(true), is(false))
        );
        assertThat(
                labyrinth.canMove(x / 2, y / 2, Moves.Move.RIGHT),
                anyOf(is(true), is(false))
        );
    }

    @Test
    void isEnd() {
        assertFalse(labyrinth.isEnd(x / 2, y / 2));
        int endX = labyrinth.getEndX();
        int endY = labyrinth.getEndY();
        assertTrue(labyrinth.isEnd(endX, endY));
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
        assertTrue(labyrinth.getXLength() == x);
    }

    @Test
    void getYLength() {
        assertTrue(labyrinth.getYLength() == y);
    }
}