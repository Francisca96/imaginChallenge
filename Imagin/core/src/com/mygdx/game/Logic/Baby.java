package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Joao on 05-11-2016.
 */
public class Baby extends Character {

    public Baby(float x, float y, int frames, World world){
        super(x, y, frames, world);
        texture = new Texture("baby3.png");
        animation = new Animation(new TextureRegion(texture), frames, 0.5f);
        animation.isMoving = true;
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
        // body.setTransform(body.getPosition().x, body.getPosition().y+3f, 0);
        //bdef.position.add(0, 5);
        //position.add(0,5);
        texture = new Texture("baby1.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(0,0.0000001f);
        body.setTransform(body.getPosition().x, body.getPosition().y+3f, 0);
    }

    public void moveDown(){
        // body.applyLinearImpulse(new Vector2(0,-2f), body.getWorldCenter(), true);
        //body.applyForceToCenter(0,-5,true);

        //position.add(0,-5);
        texture = new Texture("baby3.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(0,-0.0000001f);
        body.setTransform(body.getPosition().x, body.getPosition().y-3f, 0);
    }

    public void moveLeft(){
        // body.applyLinearImpulse(new Vector2(-2f,0), body.getWorldCenter(), true);
        //bdef.position.set(getPositionX() - 5,getPositionY());
        //body.setTransform(body.getPosition().x-3f, body.getPosition().y, 0);
        //position.add(-5,0);
        texture = new Texture("baby4.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(-0.0000001f, 0);
        body.setTransform(body.getPosition().x - 3f, body.getPosition().y, 0);
    }

    public void moveRight(){
        // bdef.position.set(getPositionX() + 5,getPositionY());
        //body.applyLinearImpulse(new Vector2(2f,0), body.getWorldCenter(), true);
        //position.add(5,0);
        texture = new Texture("baby2.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(0.0000001f, 0);
        body.setTransform(body.getPosition().x + 3f, body.getPosition().y, 0);
    }

    public void startMoving(){
        animation.isMoving = true;
    }

}
