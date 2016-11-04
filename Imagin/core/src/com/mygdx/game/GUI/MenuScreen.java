package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Created by Inês on 04/11/2016.
 */
public class MenuScreen implements Screen{

    private Imagin game;
    private OrthographicCamera cam;
    private Viewport menuPort;
    private Stage stage;

    //Variables of textures
    private Texture background;

    public MenuScreen(Imagin game){
        this.game = game;
        this.background = new Texture("background.jpg");

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);

        initStage(game.batch);
    }

    public void handleInput(float dt) {
    }

    public void update(float dt){
        handleInput(dt);
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
        game.batch.draw(background,0, 0, Imagin.V_WIDTH, Imagin.V_HEIGHT);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        game.dispose();
        background.dispose();
        stage.dispose();

    }

    public void initStage(SpriteBatch batch) {
        this.stage = new Stage(menuPort, batch);

        Gdx.input.setInputProcessor(stage);
    }
}
