package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.mygdx.game.Logic.Boy;
import com.mygdx.game.Logic.Character;

/**
 * Created by Inês on 04/11/2016.
 */
public class StoreScreen implements Screen{

    private Imagin game;
    private OrthographicCamera cam;
    private Viewport menuPort;
    private Stage stage;

    private Texture blue;
    private Texture freeze;
    private TextureAtlas lvlMenuAtlas;
    private Skin skin;
    private ImageButton backBtn;
    private ImageButton buyBtn;

    public StoreScreen(Imagin game) {
        this.game = game;
        this.blue = new Texture("shop_background.jpg");
        this.freeze = new Texture("ice-cube.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);

        initStage(game.batch);
    }

    public void handleInput(float dt) {

        if (backBtn.isPressed()) game.setScreen(new MenuScreen(game));
    }

    public void update(float dt){
        handleInput(dt);
        //boy.update(dt);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(blue,0, 0, Imagin.V_WIDTH, Imagin.V_HEIGHT);
        game.batch.draw(freeze, Imagin.V_WIDTH / 2 - 120, Imagin.V_HEIGHT/2 -220,60,60);
        game.batch.end();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        game.dispose();
        blue.dispose();
        stage.dispose();

    }

    public void initStage(SpriteBatch batch) {
        this.stage = new Stage(menuPort, batch);

        lvlMenuAtlas = new TextureAtlas("more.pack");
        skin = new Skin();
        skin.addRegions(lvlMenuAtlas);
        stage.clear();

        backBtn = new ImageButton(skin.getDrawable("backBtn"));
        backBtn.setSize(150,150);
        backBtn.setPosition(Imagin.V_WIDTH /2 -250,Imagin.V_HEIGHT /2 - 400);
        stage.addActor(backBtn);

        buyBtn = new ImageButton(skin.getDrawable("buy"));
        buyBtn.setSize(110,110);
        buyBtn.setPosition(Imagin.V_WIDTH / 2-20,Imagin.V_HEIGHT/2 -250);
        stage.addActor(buyBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
