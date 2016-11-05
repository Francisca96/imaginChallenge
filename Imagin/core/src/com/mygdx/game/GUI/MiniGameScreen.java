package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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

        player = new MiniBoy(230,2000);

        gamePort = new FitViewport(480,800,gameCam);
        gameCam.position.set(240, 2000, 0);
        //gameCam.translate(240 , 2000);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("miniGameBg.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);


    }


    public World getWorld() {
        return world;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
          player.move = true;
        }


    }

    public void update(float dt){
        handleInput(dt);
        player.update(dt);
        if(gameCam.position.y <= 400){
            gameCam.position.y = 400;

        }
        else {
            gameCam.position.y = player.getPosition().y;
        }
        gameCam.update();
        renderer.setView(gameCam);






    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined);

        renderer.render();
        game.batch.begin();

        game.batch.draw(player.getTexture(),player.getPosition().x,player.getPosition().y);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
