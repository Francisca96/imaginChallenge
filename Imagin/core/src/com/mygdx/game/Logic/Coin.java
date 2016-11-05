package com.mygdx.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Joao on 05-11-2016.
 */
public class Coin {
    private Vector2 position;
    private int frames;
    public Animation animation;
    private Texture texture;
    public Rectangle bounds;

    public Coin(float x, float y, int frames){
        position = new Vector2(x, y);
        texture = new Texture("coin.png");
        animation = new Animation(new TextureRegion(texture), frames, 0.5f);
        animation.isMoving = true;
        bounds = new Rectangle(position.x, position.y, animation.getFrame().getRegionWidth(), animation.getFrame().getRegionHeight());
    }

    public float getPositionX() {
        return position.x;
    }

    public float getPositionY() {
        return position.y;
    }

    public void update(float dt){
        animation.update(dt);
    }

    public TextureRegion getTexture()
    {
        return animation.getFrame();
    }

    public boolean colides(Rectangle player){
        return bounds.overlaps(player);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        bounds = null;
        animation.dispose();
    }
}
