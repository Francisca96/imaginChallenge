package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    private Texture title;
    private Texture guy;
    private TextureAtlas lvlMenuAtlas;
    private Skin skin;


    //Inicializar botoes
    private ImageButton playBtn;
    private ImageButton shopBtn;

    public MenuScreen(Imagin game){
        this.game = game;
        this.background = new Texture("background.jpg");

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);

        initStage(game.batch);
    }

    public void handleInput(float dt) {

        if (playBtn.isPressed()) game.setScreen(new PlayScreen(game));
        if(shopBtn.isPressed()) game.setScreen(new StoreScreen(game));

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

        lvlMenuAtlas = new TextureAtlas("buttons_menu.pack");
        skin = new Skin();
        skin.addRegions(lvlMenuAtlas);
        stage.clear();

        //PlayButton
        playBtn = new ImageButton(skin.getDrawable("play"));
        playBtn.setSize(playBtn.getWidth(),playBtn.getHeight());
        playBtn.setPosition(Imagin.V_WIDTH /2 - playBtn.getWidth()/2,Imagin.V_HEIGHT /2 - 170);
        stage.addActor(playBtn);

        //ShopButton
        shopBtn = new ImageButton(skin.getDrawable("shop"));
        shopBtn.setSize(shopBtn.getWidth(),shopBtn.getHeight());
        shopBtn.setPosition(Imagin.V_WIDTH /2 - shopBtn.getWidth()/2, Imagin.V_HEIGHT/2 - 250);
        stage.addActor(shopBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
