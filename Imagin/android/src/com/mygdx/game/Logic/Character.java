package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joao on 04-11-2016.
 */
public abstract class Character {
    protected Vector2 position;
    protected Animation animation;
    protected Texture texture;

    protected Character(float x, float y, int frames){
        position = new Vector2(x, y);

        texture = new Texture("img");

        animation = new Animation(new TextureRegion(texture), frames, 0.5f);
    }

    public abstract float getPositionX();
    public abstract float getPositionY();
    public abstract Texture getTexture();
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void dispose();
}