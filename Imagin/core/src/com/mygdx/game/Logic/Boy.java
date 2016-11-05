package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Joao on 04-11-2016.
 */
public class Boy extends Character {
    public Boy(float x, float y, int frames, World world){
        super(x, y, frames, world);
        texture = new Texture("03.png");
        animation = new Animation(new TextureRegion(texture), frames, 0.5f);
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
    public TextureRegion getFrames() {
        return animation.getFrame();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        rectangle.setPosition(bdef.position.x-16,bdef.position.y-16);
        body.setTransform(position, 0);

    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public Rectangle getBoundaries(){
        return rectangle;
    }

    public void moveUp(){
        position.add(0,5);
        texture = new Texture("04.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);
    }

    public void moveDown(){
        bdef.position.set(getPositionX(),getPositionY() - 5);
        position.add(0,-5);
        texture = new Texture("01.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);
    }

    public void moveLeft(){
        bdef.position.set(getPositionX() + 5,getPositionY());
        position.add(-5,0);
        texture = new Texture("02.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);
    }

    public void moveRight(){
        bdef.position.set(getPositionX() - 5,getPositionY());
        position.add(5,0);
        texture = new Texture("03.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);
    }


}
