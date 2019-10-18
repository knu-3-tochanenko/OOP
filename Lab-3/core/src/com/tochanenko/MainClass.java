package com.tochanenko;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MainClass extends ApplicationAdapter {
	Labyrinth labyrinth = new Labyrinth();

	private static final int LAVA_SIZE = 32;
	private static final int SMILE_SIZE = 28;
	private static final int SIZE_DEIFF = (LAVA_SIZE - SMILE_SIZE) / 2;

	SpriteBatch batch;
	SpriteBatch lavaBatch;
	Texture img;
	Texture lava;

	int posX = 0, posY = 0;

	private void goUp() {
		if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.UP))
			posY++;
		if (labyrinth.isEnd(posX, posY))
			posX = posY = 0;
	}

	private void goRight() {
		if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.RIGHT))
			posX++;
		if (labyrinth.isEnd(posX, posY))
			posX = posY = 0;
	}

	private void goLeft() {
		if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.LEFT))
			posX--;
		if (labyrinth.isEnd(posX, posY))
			posX = posY = 0;
	}

	private void goDown() {
		if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.DOWN))
			posY--;
		if (labyrinth.isEnd(posX, posY))
			posX = posY = 0;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		lavaBatch = new SpriteBatch();
		img = new Texture("smile.png");
		lava = new Texture("lava.png");

		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			goUp();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			goRight();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			goLeft();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			goDown();

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
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img,
				posX * LAVA_SIZE + SIZE_DEIFF,
				posY * LAVA_SIZE + SIZE_DEIFF,
				SMILE_SIZE, SMILE_SIZE);
		batch.end();
		lavaBatch.begin();

		int xLength = labyrinth.getXLength();
		int yLength = labyrinth.getYLength();
		for (int i = 0; i < yLength; i++)
			for (int j = 0; j < xLength; j++)
				if (labyrinth.isWall(j, i)) {
					lavaBatch.draw(lava, j * LAVA_SIZE, i * LAVA_SIZE, LAVA_SIZE, LAVA_SIZE);
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
