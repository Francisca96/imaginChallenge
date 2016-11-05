package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.mygdx.game.GUI.MiniGameScreen;
import com.mygdx.game.Imagin;

/**
 * Created by Francisca on 04/11/16.
 */
public class MiniBoy extends Sprite{
    public static final int GRAVITY = -10;
    public Vector3 position;
    public Vector3 velocity;
    private Texture miniBoy;


    public World world;
    public Body b2body;


    public Rectangle boyBounds;
    public BodyDef bodydef;

    public boolean move = false;

    public Vector3 getPosition() {
        return position;
    }

    public MiniBoy(int x, int y) {


        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        miniBoy = new Texture("bush.png");

    }

    public void update(float dt) {

        if(move == true) {
            if (position.y > 0) {
                velocity.add(0, GRAVITY, 0);
            }
            velocity.scl(dt);
            position.add(0, velocity.y, 0);


            if (position.y < 58) {
                position.y = 58;
            }
            velocity.scl(1 / dt);
        }

}

    public void definePlayer() {

    }

    public void render(SpriteBatch sb){


    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTexture(){
        return miniBoy;
    }
}
