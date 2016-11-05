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
 * Created by Inês on 05/11/2016.
 */
public class MiniHud implements Disposable{

    public Stage stage;
    public Viewport hudPort;

    private static Integer coins;
    private static Integer money;

    private static Label coinsLabel;

    public MiniHud(SpriteBatch sb){
        coins=0;
        money=0;

        hudPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(hudPort,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        coinsLabel = new Label(String.format("%04d", money), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        coinsLabel.setFontScale(2);

        table.add(coinsLabel).expandX().padTop(10);

        stage.addActor(table);

    }

    public void update(float dt){
    }

    public static void addCoin(){
        coins+=1;
        money=coins*100;
        coinsLabel.setText(String.format("%03d", money));
    }

    public int getMoney(){
        return money;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
