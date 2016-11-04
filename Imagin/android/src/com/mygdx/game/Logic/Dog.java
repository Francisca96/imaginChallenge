package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Joao on 04-11-2016.
 */
public class Dog extends Character {

    
    public Dog(float x, float y, int frames){
        super(x, y, frames);
    }

    @Override
    public float getPositionX() {
        return position.x;
    }

    @Override
    public float getPositionY() {
        return position.y;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
