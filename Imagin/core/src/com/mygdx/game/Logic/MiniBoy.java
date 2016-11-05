package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Imagin;

/**
 * Created by Francisca on 04/11/16.
 */
public class MiniBoy extends Sprite{
    public static final int GRAVITY = -8;
    public Vector2 position;
    public Vector3 velocity;
    private Texture miniBoy;

    public World world;
    public Body body;
    public BodyDef bdef;


    public Rectangle boyBounds;
    public BodyDef bodydef;
    public Rectangle bounds;

    public Animation animation;

    public boolean move;

    public MiniBoy(int x, int y, World world) {
        position = new Vector2(x,y);
        velocity = new Vector3(0,0,0);
        this.world = world;

        miniBoy = new Texture("01.png");
        TextureRegion texReg = new TextureRegion(miniBoy);
        animation = new Animation(texReg, 4, 0.5f);
        animation.isMoving = true;
        bounds = new Rectangle(position.x, position.y, texReg.getRegionWidth(), texReg.getRegionHeight());
        move = false;


    }


    public Vector2 getPosition() {
        return position;
    }

    public void update(float dt) {
        animation.update(dt);

        if(move == true) {
            if (position.y > 0) {
                velocity.add(0, GRAVITY, 0);
            }
            velocity.scl(dt);
            position.add(0, velocity.y);
            bounds.setPosition(position.x, position.y);


            if (position.y < 58) {
                position.y = 58;
            }
            velocity.scl(1 / dt);
        }
}

    public void definePlayer() {

    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTexture(){
        return miniBoy;
    }
    public void moveRandom(){


    }
}
