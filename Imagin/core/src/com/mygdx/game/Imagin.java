package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GUI.MenuScreen;
import com.mygdx.game.GUI.PlayScreen;
import com.mygdx.game.GUI.StoreScreen;

public class Imagin extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 800;
	public static final float PPM = 100;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new StoreScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
}
