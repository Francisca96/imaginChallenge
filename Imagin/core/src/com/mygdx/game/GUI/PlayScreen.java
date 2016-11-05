package com.mygdx.game.GUI;

/**
 * Created by Inês on 04/11/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Logic.Boy;
import com.mygdx.game.Logic.Character;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.Hud;


public class PlayScreen implements Screen {

    private Imagin game;
    private OrthographicCamera cam;
    private Viewport menuPort;
    private Stage stage;
    private Hud hud;

    private Texture background;
    private TextureAtlas lvlMenuAtlas;
    private Skin skin;
    private ImageButton up;
    private ImageButton down;
    private ImageButton right;
    private ImageButton left;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Character boy;

    private World world;
    private B2WorldCreator creator;

    private Box2DDebugRenderer b2dr;

    public PlayScreen(Imagin game) {
        this.game = game;
        this.background = new Texture("blue.png");

        world = new World(new Vector2(0,0), true);

        cam = new OrthographicCamera();
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("lvl1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        cam.position.set(menuPort.getWorldWidth(), menuPort.getWorldHeight(), 0);

        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        boy = new Boy(49, 397, 4, this.world);

        initStage(game.batch);
    }

    public void handleInput(float dt) {

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int cursor_x = Gdx.input.getX();
            int cursor_y = Gdx.input.getY();
            if (cursor_x >= 100 && cursor_x < 140) {
                if (cursor_y < 100 && cursor_y > 30) {
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        boy.update(dt);

        hud.update(dt);

        cam.update();

        renderer.setView(cam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, cam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.begin();
       // game.batch.draw(boy.getFrames(), boy.getPositionX()-8, boy.getPositionY()-8, 32, 32);
        boy.draw(game.batch);
        game.batch.end();

        hud.stage.draw();
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
        hud.dispose();
        stage.dispose();

    }

    public void initStage(SpriteBatch batch) {
        this.stage = new Stage(menuPort, batch);

        lvlMenuAtlas = new TextureAtlas("arrows.pack");
        skin = new Skin();
        skin.addRegions(lvlMenuAtlas);
        stage.clear();

        //Up
        up = new ImageButton(skin.getDrawable("arrow-up"));
        up.setSize(70,70);
        up.setPosition(Imagin.V_WIDTH /2 - up.getWidth()/2 - 50,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(up);

        //Left
        left = new ImageButton(skin.getDrawable("arrow-left"));
        left.setSize(70,70);
        left.setPosition(Imagin.V_WIDTH /2 - left.getWidth()/2 - 160,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(left);

        //Down
        down = new ImageButton(skin.getDrawable("arrow-down"));
        down.setSize(70,70);
        down.setPosition(Imagin.V_WIDTH /2 - down.getWidth()/2 +50 ,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(down);

        //Right
        right = new ImageButton(skin.getDrawable("arrow-right"));
        right.setSize(70,70);
        right.setPosition(Imagin.V_WIDTH /2 - right.getWidth()/2 +160 ,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(right);


        Gdx.input.setInputProcessor(stage);
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }

}
