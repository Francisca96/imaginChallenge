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

    public static int title_width = Imagin.V_WIDTH - 100;
    public static int title_height = 100;
    public static int guy_width = 270;
    public static int guy_height = 270;
    public static int btn_width = 400;
    public static int btn_height = 200;


    //Inicializar botoes
    private ImageButton playBtn;
    private ImageButton storeBtn;
    private ImageButton soundBtn;

    public MenuScreen(Imagin game){
        this.game = game;
        this.background = new Texture("background.jpg");
        this.title = new Texture("title1.png");
        this.guy = new Texture("guymenu.png");

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);

        initStage(game.batch);
    }

    public void handleInput(float dt) {

        if (playBtn.isPressed()) game.setScreen(new PlayScreen(game));

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
        game.batch.draw(title,Imagin.V_WIDTH / 2 - title_width /2, Imagin.V_HEIGHT / 2 + 200, title_width, title_height);
        game.batch.draw(guy, Imagin.V_WIDTH/2 - guy_width/2+90, Imagin.V_HEIGHT/2-400, guy_width, guy_height);
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

        lvlMenuAtlas = new TextureAtlas("buttons.pack");
        skin = new Skin();
        skin.addRegions(lvlMenuAtlas);
        stage.clear();

        //PlayButton
        playBtn = new ImageButton(skin.getDrawable("playBtn"));
        playBtn.setSize(300,150);
        playBtn.setPosition(Imagin.V_WIDTH /2 - playBtn.getWidth()/2,Imagin.V_HEIGHT /2 - 20);
        stage.addActor(playBtn);

        //OptionsButton
        storeBtn = new ImageButton(skin.getDrawable("storeBtn"));
        storeBtn.setSize(300,150);
        storeBtn.setPosition(Imagin.V_WIDTH /2 - storeBtn.getWidth()/2, Imagin.V_HEIGHT/2 - 120);
        stage.addActor(storeBtn);

        soundBtn = new ImageButton(skin.getDrawable("soundon"));
        soundBtn.setSize(80,80);
        soundBtn.setPosition(Imagin.V_WIDTH /2 - soundBtn.getWidth()/2-130, Imagin.V_HEIGHT/2 - 310);
        stage.addActor(soundBtn);

        Gdx.input.setInputProcessor(stage);
    }
}
