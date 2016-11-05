package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.mygdx.game.Logic.Coin;
import com.mygdx.game.Logic.MiniBoy;
import com.mygdx.game.Tools.MiniHud;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Francisca on 04/11/16.
 */
public class MiniGameScreen implements Screen, InputProcessor{
    private Imagin game;
    private MiniHud minihud;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private MapProperties prop;

    private int mapWidth;
    private int mapHeight;
    private MiniBoy player;
    private ArrayList<Coin> coins;

    private World world;

    private Box2DDebugRenderer b2dr;
    public int money;




    public MiniGameScreen(Imagin game){
        this.game = game;
        gameCam = new OrthographicCamera();

        world = new World(new Vector2(0, 0), true);

        player = new MiniBoy(230,2000, world);

        gamePort = new FitViewport(480,800,gameCam);
        gameCam.position.set(240, 2000, 0);
        //gameCam.translate(240 , 2000);
        minihud = new MiniHud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("miniGameBg.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        coins = new ArrayList<Coin>();
        genCoins();
        money = 0;

        Gdx.input.setInputProcessor(this);




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
        for(Coin c : coins){
            c.update(dt);
        }
        if(gameCam.position.y <= 400){
            gameCam.position.y = 400;


        }
        else {
            gameCam.position.y = player.getPosition().y;
        }
        gameCam.update();
        renderer.setView(gameCam);


        for(int i = 0; i < coins.size(); i++){
            if(coins.get(i).bounds.overlaps(player.bounds)){
                coins.get(i).dispose();
                coins.remove(i);
                minihud.addCoin();
                game.setMoney(minihud.getMoney());
            }
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gameCam.combined);

        renderer.render();
        game.batch.begin();

        game.batch.draw(player.animation.getFrame(),player.position.x,player.position.y);
        for (Coin c: coins){
            if (c.bounds.overlaps(player.bounds) == false)
                game.batch.draw(c.animation.getFrame(), c.getPositionX(), c.getPositionY());
        }
        game.batch.end();

        minihud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(player.position.y <=58)
            player.move = false;
        if(player.move == true) {
            if (screenX < Imagin.V_WIDTH / 2 && screenX > 16) {
                player.position.add(-20, 0);
            } else if (screenX >= Imagin.V_WIDTH / 2 && screenX < Imagin.V_WIDTH - 16) {
                player.position.add(20, 0);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(Coin c : coins)
            c.dispose();
    }

    public void genCoins(){
        Random rand = new Random();
        for (int y = 1950; y >= 250; y-=33){
            int x = rand.nextInt(488) + 16;
            Coin c = new Coin(x, y, 7);
            coins.add(c);
        }
    }
}
