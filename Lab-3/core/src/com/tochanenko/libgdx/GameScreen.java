package com.tochanenko.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tochanenko.user.Labyrinth;
import com.tochanenko.user.Move;


public class GameScreen implements Screen {
    final GameCore gameCore;

    public GameScreen(GameCore gameCore) {
        this.gameCore = gameCore;

        batch = new SpriteBatch();
        lavaBatch = new SpriteBatch();
        img = new Texture("slime.png");
        lava = new Texture("lava.png");

        labyrinth = new Labyrinth(
                Gdx.graphics.getWidth() / BLOCK_SIZE,
                Gdx.graphics.getHeight() / BLOCK_SIZE
        );

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                goUp();
            }

            @Override
            public void onRight() {
                goRight();
            }

            @Override
            public void onLeft() {
                goLeft();
            }

            @Override
            public void onDown() {
                goDown();
            }
        }));
    }

    private static final int BLOCK_SIZE = 32;
    private static final int CHARACTER_SIZE = 28;
    private static final int SIZE_DIFF = (BLOCK_SIZE - CHARACTER_SIZE) / 2;

    private Labyrinth labyrinth;

    private SpriteBatch lavaBatch;
    private SpriteBatch batch;
    private Texture lava;
    private Texture img;

    private int posX = 0, posY = 0;

    private void goIfPossible(Move moveTo, int changeX, int changeY) {
        if (labyrinth.canMove(posX, posY, moveTo)) {
            posX += changeX;
            posY += changeY;
        }
        if (labyrinth.isEnd(posX, posY)) {
            gameCore.setScreen(new MainMenuScreen(gameCore));
        }
    }

    private void goUp() {
        goIfPossible(Move.UP, 0, 1);
    }

    private void goRight() {
        goIfPossible(Move.RIGHT, 1, 0);
    }

    private void goLeft() {
        goIfPossible(Move.LEFT, -1, 0);
    }

    private void goDown() {
        goIfPossible(Move.DOWN, 0, -1);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.W))
            goUp();
        if (Gdx.input.isKeyJustPressed(Input.Keys.D))
            goRight();
        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            goLeft();
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
            goDown();
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            gameCore.setScreen(new MainMenuScreen(gameCore));
        }

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img,
                posX * BLOCK_SIZE + SIZE_DIFF,
                posY * BLOCK_SIZE + SIZE_DIFF,
                CHARACTER_SIZE, CHARACTER_SIZE);
        batch.end();
        lavaBatch.begin();

        int xLength = labyrinth.getXLength();
        int yLength = labyrinth.getYLength();
        for (int i = 0; i < yLength; i++)
            for (int j = 0; j < xLength; j++)
                if (labyrinth.isWall(j, i)) {
                    lavaBatch.draw(lava,
                            j * BLOCK_SIZE,
                            i * BLOCK_SIZE,
                            BLOCK_SIZE, BLOCK_SIZE);
                }
        lavaBatch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        lavaBatch.dispose();
        img.dispose();
    }
}
