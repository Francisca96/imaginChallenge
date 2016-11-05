package com.mygdx.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Imagin;

/**
 * Created by Joao on 04-11-2016.
 */
public class Boy extends Character {
    public Boy(float x, float y, int frames, World world){
        super(x, y, frames, world);
        texture = new Texture("01.png");
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

        if(body.getPosition().x <= 4) body.setTransform(4,body.getPosition().y,0);

        if(body.getPosition().x >= Imagin.V_WIDTH- 4)
            body.setTransform(Imagin.V_WIDTH-4 ,body.getPosition().y,0);
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
        texture = new Texture("04.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(0,0.0000001f);
        body.setTransform(body.getPosition().x, body.getPosition().y+3f, 0);
    }

    public void moveDown(){
       // body.applyLinearImpulse(new Vector2(0,-2f), body.getWorldCenter(), true);
        //body.applyForceToCenter(0,-5,true);

        //position.add(0,-5);
        texture = new Texture("01.png");
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
        texture = new Texture("02.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(-0.0000001f, 0);
        body.setTransform(body.getPosition().x - 3f, body.getPosition().y, 0);
    }

    public void moveRight(){
       // bdef.position.set(getPositionX() + 5,getPositionY());
        //body.applyLinearImpulse(new Vector2(2f,0), body.getWorldCenter(), true);
        //position.add(5,0);
        texture = new Texture("03.png");
        animation = new Animation(new TextureRegion(texture), 4, 0.5f);

        animation.isMoving = true;

        body.setLinearVelocity(0.0000001f, 0);
        body.setTransform(body.getPosition().x + 3f, body.getPosition().y, 0);
    }

    public void startMoving(){
        animation.isMoving = true;
    }

}
