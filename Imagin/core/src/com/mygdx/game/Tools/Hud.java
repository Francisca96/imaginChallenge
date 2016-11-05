package com.mygdx.game.Tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;


/**
 * Created by Inês on 04/11/2016.
 */
public class Hud implements Disposable{

    public Stage stage;
    public Viewport hudPort;

    private float timeCount;

    private Label restartLabel;
    private static Label levelLabel;
    private static Label timerLabel;

    private Texture restartTexture;
    private Image restart;

    public Hud(SpriteBatch sb){
        timeCount =0;

        hudPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(hudPort,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        restartTexture = new Texture("restartBtn.png");
        restart = new Image(restartTexture);
        levelLabel = new Label("Level 1", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel.setFontScale(2);
        timerLabel = new Label(String.format("%03d", Math.round(timeCount)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timerLabel.setFontScale(2);

        table.add(restart).expandX().padTop(18); //pause button alligned with the others
        table.add(levelLabel).expandX().padTop(10);
        table.add(timerLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    public void update(float dt){

        timeCount += dt;
        if(timeCount >= 1)  timeCount = 0.8f;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
