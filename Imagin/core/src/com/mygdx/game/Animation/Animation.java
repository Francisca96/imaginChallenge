package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Joao on 05-11-2016.
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private boolean isIncreasing;
    public boolean isMoving;


    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        isIncreasing = true;
        isMoving = false;
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        if(isMoving == true) {
            currentFrameTime += dt;
            if (frame == 0)
                isIncreasing = true;
            else if (frame == frameCount - 1) {
                isIncreasing = false;
            }
            if (currentFrameTime > maxFrameTime) {
                currentFrameTime = 0;
                if (isIncreasing == true)
                    frame++;
                else
                    frame--;
            }
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

    public void dispose(){
        for(TextureRegion frame : frames)
            frame.getTexture().dispose();
    }
}
