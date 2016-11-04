package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Joao on 04-11-2016.
 */
public abstract class Character extends Sprite{
    protected Vector2 position;
   // protected Animation animation;
    protected Texture texture;
    protected World world;
    protected Body body;
    public Rectangle rectangle;


    protected Character(float x, float y, int frames, World world){
        position = new Vector2(x, y);
        this.world = world;
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x,position.y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fdef.shape = shape;
        shape.setAsBox(16,16);

        body.createFixture(fdef).setUserData(this);

        rectangle.set(body.getPosition().x -16, body.getPosition().y -16,32,32);

        texture = new Texture("img");

        //animation = new Animation(new TextureRegion(texture), frames, 0.5f);
    }

    public abstract float getPositionX();
    public abstract float getPositionY();
    public abstract Texture getTexture();
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void dispose();
    public abstract Rectangle getBoundaries();
}