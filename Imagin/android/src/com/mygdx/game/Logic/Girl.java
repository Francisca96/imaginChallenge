package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Joao on 04-11-2016.
 */
public class Girl extends Character {



    public Girl(float x, float y, int frames, World world){
        super(x, y, frames, world);
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

    @Override
    public Rectangle getBoundaries(){
        return rectangle;
    }
}
