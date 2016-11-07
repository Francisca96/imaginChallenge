package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GUI.MenuScreen;
import com.mygdx.game.GUI.MiniGameScreen;


public class Imagin extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 512;
	public static final int V_HEIGHT = 800;
	public static final float PPM = 100;
	public Music music;

	public int money;

	public boolean musicB;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(1);
		music.play();
		musicB = true;
		money=0;

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}

	public boolean getMusicB(){
		return musicB;
	}

	public int getMoney(){
		return money;
	}

	public void setMoney(int money){
		this.money+=money;
	}
}
