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
import com.mygdx.game.Imagin;

/**
 * Created by Francisca on 04/11/16.
 */
public class MiniBoy extends Sprite{
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture miniBoy;


    public World world;
    //public MiniGameScreen screen;
    public Body b2body;


    public Rectangle boyBounds;
    public BodyDef bodydef;


    public MiniBoy(World world) {
        this.world=world;
        //this.screen = screen;
        boyBounds = new Rectangle();

        miniBoy = new Texture("grass.png");

        definePlayer();
    }

    public void update(float dt) {


        setPosition(b2body.getPosition().x - 32/Imagin.PPM, b2body.getPosition().y - 32/Imagin.PPM);

        if(b2body.getPosition().x <=100) b2body.setTransform(0.35f, b2body.getPosition().y,0);
        if(b2body.getPosition().x >= Imagin.V_WIDTH/Imagin.PPM - 0.3f) b2body.setTransform(Imagin.V_WIDTH/Imagin.PPM - 0.3f, b2body.getPosition().y,0);

        boyBounds.setPosition(b2body.getPosition().x-32 / Imagin.PPM, b2body.getPosition().y - 32 /Imagin.PPM);
    }

    public void definePlayer() {
        bodydef = new BodyDef();
        bodydef.position.set(100 / Imagin.PPM, 32 / Imagin.PPM);
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(24/Imagin.PPM, 16/Imagin.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
        boyBounds.set(b2body.getPosition().x-32 /Imagin.PPM, b2body.getPosition().y - 32 / Imagin.PPM, 64/ Imagin.PPM, 64/Imagin.PPM);
    }

    public void render(SpriteBatch sb){

        sb.draw(miniBoy,b2body.getPosition().x - 32 / Imagin.PPM, b2body.getPosition().y - 32 / Imagin.PPM , 64/Imagin.PPM, 64/Imagin.PPM);
    }
}
