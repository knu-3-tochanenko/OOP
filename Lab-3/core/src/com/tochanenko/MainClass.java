package com.tochanenko;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainClass extends ApplicationAdapter {
	private static final int BLOCK_SIZE = 32;
	private static final int CHARACTER_SIZE = 28;
	private static final int SIZE_DIFF = (BLOCK_SIZE - CHARACTER_SIZE) / 2;

	private Labyrinth labyrinth;

	private SpriteBatch lavaBatch;
	private SpriteBatch batch;
	private Texture lava;
	private Texture img;

	private int posX = 0, posY = 0;

	private void goIfPossible(Moves.Move moveTo, int changeX, int changeY) {
		if (labyrinth.canMove(posX, posY, moveTo)) {
			posX += changeX;
			posY += changeY;
		}
		if (labyrinth.isEnd(posX, posY))
			posX = posY = 0;
	}

	private void goUp() {
		goIfPossible(Moves.Move.UP, 0, 1);
	}

	private void goRight() {
		goIfPossible(Moves.Move.RIGHT, 1, 0);
	}

	private void goLeft() {
		goIfPossible(Moves.Move.LEFT, -1, 0);
	}

	private void goDown() {
		goIfPossible(Moves.Move.DOWN, 0, -1);
	}
	
	@Override
	public void create () {
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

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.W))
			goUp();
		if(Gdx.input.isKeyJustPressed(Input.Keys.D))
			goRight();
		if(Gdx.input.isKeyJustPressed(Input.Keys.A))
			goLeft();
		if(Gdx.input.isKeyJustPressed(Input.Keys.S))
			goDown();

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
	public void dispose () {
		batch.dispose();
		lavaBatch.dispose();
		img.dispose();
	}
}
