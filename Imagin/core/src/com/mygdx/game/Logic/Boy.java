package com.mygdx.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Joao on 04-11-2016.
 */
public class Boy extends Character {
    public Boy(float x, float y, int frames, World world){
        super(x, y, frames, world);
        texture = new Texture("01.png");
        animation = new Animation(new TextureRegion(texture), frames, 0.5f);
        setRegion(getFrames());
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
    public void draw(Batch batch) {
        super.draw(batch);
    }

    @Override
    public void update(float dt) {
        handleInput();
        animation.update(dt);
       /* body.setTransform(bdef.position.x+8, bdef.position.y + 8, 0);
        rectangle.setPosition(bdef.position.x+8,bdef.position.y+8);*/
        setBounds(0, 0, 32, 32);
        setRegion(getFrames());
        setPosition(body.getPosition().x - 16, body.getPosition().y - 16);
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
        body.setTransform(body.getPosition().x, body.getPosition().y+3f, 0);
        //bdef.position.add(0, 5);
        //position.add(0,5);
        texture = new Texture("04.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;
    }

    public void moveDown(){
        //body.applyLinearImpulse(new Vector2(3f,0), body.getWorldCenter(), true);
        body.setTransform(body.getPosition().x, body.getPosition().y-3f, 0);
        //position.add(0,-5);
        Gdx.app.log("" + bdef.position.y, "");
        texture = new Texture("01.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;
    }

    public void moveLeft(){
        //bdef.position.set(getPositionX() - 5,getPositionY());
        body.setTransform(body.getPosition().x-3f, body.getPosition().y, 0);
        //position.add(-5,0);
        texture = new Texture("02.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;
    }

    public void moveRight(){
       // bdef.position.set(getPositionX() + 5,getPositionY());
        body.setTransform(body.getPosition().x+3f, body.getPosition().y, 0);
        //position.add(5,0);
        texture = new Texture("03.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;
    }

    public void startMoving(){
        animation.isMoving = true;
    }

}
