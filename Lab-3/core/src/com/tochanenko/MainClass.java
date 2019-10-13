package com.tochanenko;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MainClass extends ApplicationAdapter {
	Labyrinth labyrinth = new Labyrinth();

	SpriteBatch batch;
	SpriteBatch lavaBatch;
	Texture img;
	Texture lava;

	int posX = 0, posY = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		lavaBatch = new SpriteBatch();
		img = new Texture("smile.png");
		lava = new Texture("lava.png");

		Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

			@Override
			public void onUp() {
				if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.UP))
					posY++;
				if (labyrinth.isEnd(posX, posY))
					posX = posY = 0;
			}

			@Override
			public void onRight() {
				if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.RIGHT))
				posX++;
				if (labyrinth.isEnd(posX, posY))
					posX = posY = 0;
			}

			@Override
			public void onLeft() {
				if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.LEFT))
					posX--;
				if (labyrinth.isEnd(posX, posY))
					posX = posY = 0;
			}

			@Override
			public void onDown() {
				if (labyrinth.canMove(posX, posY, Labyrinth.MOVE.DOWN))
				posY--;
				if (labyrinth.isEnd(posX, posY))
					posX = posY = 0;
			}
		}));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, posX * 64 + 7, posY * 64 + 7, 50, 50);
		batch.end();
		lavaBatch.begin();

		int xLength = labyrinth.getXLength();
		int yLength = labyrinth.getYLength();
		for (int i = 0; i < yLength; i++)
			for (int j = 0; j < xLength; j++)
				if (labyrinth.isWall(j, i)) {
					lavaBatch.draw(lava, j * 64, i * 64, 64, 64);
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
