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
	short [][] matrix = {
			{0, 0, 0, 0, 1},
			{1, 0, 0, 0, 1},
			{1, 0, 1, 1, 1},
			{1, 0, 0, 0, 1},
			{1, 1, 1, 0, 1}
	};

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

				if (posY < (matrix.length - 1) && matrix[posY + 1][posX] != 1)
					posY++;
				if (isEnd())
					posX = posY = 0;
			}

			@Override
			public void onRight() {
				if (posX < (matrix.length - 1) && matrix[posY][posX + 1] != 1)
				posX++;
				if (isEnd())
					posX = posY = 0;
			}

			@Override
			public void onLeft() {
				if (posX > 0 && matrix[posY][posX - 1] != 1)
					posX--;
				if (isEnd())
					posX = posY = 0;
			}

			@Override
			public void onDown() {
				if (posY > 0 && matrix[posY - 1][posX] != 1)
				posY--;
				if (isEnd())
					posX = posY = 0;
			}
		}));
	}

	private boolean isEnd() {
		return (posY == matrix.length - 1 || posX == matrix.length - 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, posX * 64 + 7, posY * 64 + 7, 50, 50);
		batch.end();
		lavaBatch.begin();
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				if (matrix[i][j] == 1) {
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
