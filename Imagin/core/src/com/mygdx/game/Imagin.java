package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GUI.MenuScreen;
import com.mygdx.game.GUI.MiniGameScreen;


public class Imagin extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 512;
	public static final int V_HEIGHT = 800;
	public static final float PPM = 100;

	public int money;

	public boolean music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		music = true;
		money=0;

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

	public boolean getMusic(){
		return music;
	}

	public int getMoney(){
		return money;
	}

	public void setMoney(int money){
		this.money+=money;
	}
}
