package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.mygdx.game.Logic.MiniBoy;

/**
 * Created by Francisca on 04/11/16.
 */
public class MiniGameScreen implements Screen {
    private Imagin game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private MapProperties prop;

    private int mapWidth;
    private int mapHeight;
    private MiniBoy player;

    private World world;

    private Box2DDebugRenderer b2dr;


    public MiniGameScreen(Imagin game){
        this.game = game;

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false);
        gamePort = new FitViewport(Imagin.V_WIDTH / Imagin.PPM, Imagin.V_HEIGHT / Imagin.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("miniGameBg.tmx");
        prop = map.getProperties();
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Imagin.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()*3, 0);

        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        player = new MiniBoy(world);

        mapWidth = prop.get("width", Integer.class).intValue() * prop.get("tilewidth", Integer.class).intValue();
        mapHeight = prop.get("height", Integer.class).intValue() * prop.get("tileheight", Integer.class).intValue();
    }


    public World getWorld() {
        return world;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gameCam.position.y -= 10*dt;
        }
    }

    public void update(float dt){
        handleInput(dt);

        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        game.batch.begin();
        player.render(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
