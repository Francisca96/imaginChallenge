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

    private Texture background;
    private Texture title;
    private TextureAtlas lvlMenuAtlas;
    private Skin skin;
    private Character boy;
    private World world;



    public StoreScreen(Imagin game) {
        this.game = game;
        this.background = new Texture("background.jpg");
        this.title = new Texture("store_title.PNG");

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);
        world = new World(new Vector2(0, 0), true);
        boy = new Boy(100,250,4,world);

        initStage(game.batch);
    }

    public void handleInput(float dt) {
        if(Gdx.input.isTouched())
            boy.moveDown();
    }

    public void update(float dt){
        handleInput(dt);
        boy.update(dt);
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
        game.batch.draw(title,Imagin.V_WIDTH/2 - title.getWidth()/2, Imagin.V_HEIGHT/2-title.getHeight()/2);
        game.batch.draw(boy.getFrames(), boy.getPositionX(), boy.getPositionY(), 64, 64);
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
        background.dispose();
        title.dispose();
        stage.dispose();

    }

    public void initStage(SpriteBatch batch) {
        this.stage = new Stage(menuPort, batch);

        Gdx.input.setInputProcessor(stage);
    }
}
