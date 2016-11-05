package com.mygdx.game.GUI;

/**
 * Created by Inês on 04/11/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Imagin;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Logic.Baby;
import com.mygdx.game.Logic.Boy;
import com.mygdx.game.Logic.Character;
import com.mygdx.game.Logic.Dog;
import com.mygdx.game.Logic.Girl;
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

    private Character boy, girl, dog, baby;

    private World world;
    private B2WorldCreator creator1;


  //  private Box2DDebugRenderer b2dr;

    private Music music;

    private int win = 0;

    public boolean level2 = true;


    public PlayScreen(Imagin game) {
        this.game = game;
        this.background = new Texture("blue.png");

        world = new World(new Vector2(0,0), true);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getBody().getUserData() =="boy" && contact.getFixtureB().getBody().getUserData()=="girl")
                    win = 1;

                if(contact.getFixtureA().getBody().getUserData()=="boy" && contact.getFixtureB().getBody().getUserData() == "dog")
                    win = 2;

                if(contact.getFixtureA().getBody().getUserData()=="boy" && contact.getFixtureB().getBody().getUserData() == "baby")
                    win = 1;

                if(contact.getFixtureA().getBody().getUserData()=="baby" && contact.getFixtureB().getBody().getUserData() == "dog")
                    win = 2;
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });



        cam = new OrthographicCamera();
        menuPort = new FitViewport(Imagin.V_WIDTH, Imagin.V_HEIGHT,cam);
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        if(level2 == false ){
            map = mapLoader.load("lvl1.tmx");

        }
        else if(level2 == true){
            map = mapLoader.load("level2.tmx");

        }
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        cam.position.set(menuPort.getWorldWidth(), menuPort.getWorldHeight(), 0);

        //b2dr = new Box2DDebugRenderer();

        creator1 = new B2WorldCreator(this);


        if (level2 == false) {
            boy = new Boy(30, 596, 4, this.world);
            boy.body.setUserData("boy");
            girl = new Girl(490, 250, 4, this.world);
            girl.body.setUserData("girl");

        }
        if(level2 == true){
            boy = new Boy(17, 320, 4, this.world);
            boy.body.setUserData("boy");
            dog = new Dog(495,530,3,this.world);
            dog.body.setUserData("dog");
            baby = new Baby(350,600,6,this.world);
            baby.body.setUserData("baby");


        }



        if (game.getMusic() == true){
            music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
            music.setLooping(true);
            music.setVolume(1);
            music.play();
        }

        initStage(game.batch);
    }

    public void handleInput(float dt) {
        if (up.isPressed()) {
            if(level2 == false){
                boy.moveUp();
                girl.moveDown();
            }

            if(level2 == true){
                boy.moveUp();
                dog.moveRight();
                dog.moveDown();

            }

        }
        if (down.isPressed()) {
            if(level2 == false){
                boy.moveDown();
                girl.moveUp();
            }

            if(level2 == true){
                boy.moveDown();
                dog.moveRight();
                dog.moveUp();
            }
        }
        if (left.isPressed()) {

            if(level2 == false){
                boy.moveLeft();
                girl.moveRight();
            }
            if(level2 == true){
                boy.moveLeft();
                dog.moveUp();
                dog.moveLeft();

            }
        }
        if (right.isPressed()) {

            if(level2 == false){
                boy.moveRight();
                girl.moveLeft();
            }
            if(level2 == true){
                boy.moveRight();
                dog.moveDown();
                dog.moveLeft();

            }
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            int cursor_x = Gdx.input.getX();
            int cursor_y = Gdx.input.getY();
            if (cursor_x >= 50 && cursor_x < 180) {
                if (cursor_y < 270 && cursor_y > 100) {
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
    }


    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);


        if (level2 == false) {
            boy.update(dt);
            girl.update(dt);
        }

        if(level2 == true){
            boy.update(dt);
            dog.update(dt);
            baby.update(dt);
        }


<<<<<<< Updated upstream

        if(win == 1){
            //this.dispose();
            game.setScreen(new MiniGameScreen(this.game));
        }

        if(win == 2){
            game.setScreen(new GameOverScreen(this.game));
=======
        if(win == true){
            game.setScreen(new MiniGameScreen(this.game));
>>>>>>> Stashed changes
        }

        hud.update(dt);
        if(hud.timeOver()){
            win = 2;
            game.setScreen(new GameOverScreen(this.game));

        }

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

        //b2dr.render(world, cam.combined);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.begin();
        if(level2 == false){
            boy.draw(game.batch);
            girl.draw(game.batch);
        }

        if(level2 == true){
            boy.draw(game.batch);
            dog.draw(game.batch);
            baby.draw(game.batch);
        }

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
        world.dispose();
        lvlMenuAtlas.dispose();
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
        up.setPosition(Imagin.V_WIDTH /2 - up.getWidth()/2  +80,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(up);

        up.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.startMoving();
                    girl.startMoving();
                }

                if(level2 == true){
                    boy.startMoving();
                    dog.startMoving();
                }

                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.animation.isMoving = false;
                    girl.animation.isMoving = false;
                }

                if(level2 == true){
                    boy.animation.isMoving = false;
                    dog.animation.isMoving = false;
                }


            }
        });

        //Left
        left = new ImageButton(skin.getDrawable("arrow-left"));
        left.setSize(70,70);
        left.setPosition(Imagin.V_WIDTH /2 - left.getWidth()/2 - 160,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(left);

        left.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.startMoving();
                    girl.startMoving();
                }

                if(level2 == true){
                    boy.startMoving();
                    dog.startMoving();
                }
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.animation.isMoving = false;
                    girl.animation.isMoving = false;
                }

                if(level2 == true){
                    boy.animation.isMoving = false;
                    dog.animation.isMoving = false;
                }
            }
        });

        //Down
        down = new ImageButton(skin.getDrawable("arrow-down"));
        down.setSize(70,70);
        down.setPosition(Imagin.V_WIDTH /2 - down.getWidth()/2 +170 ,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(down);

        down.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.startMoving();
                    girl.startMoving();
                }

                if(level2 == true){
                    boy.startMoving();
                    dog.startMoving();
                }


                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.animation.isMoving = false;
                    girl.animation.isMoving = false;
                }

                if(level2 == true){
                    boy.animation.isMoving = false;
                    dog.animation.isMoving = false;
                }
            }
        });

        //Right
        right = new ImageButton(skin.getDrawable("arrow-right"));
        right.setSize(70,70);
        right.setPosition(Imagin.V_WIDTH /2 - right.getWidth()/2 -50 ,Imagin.V_HEIGHT /2 - 350);
        stage.addActor(right);

        right.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                if(level2 == false){
                    boy.startMoving();
                    girl.startMoving();
                }
                if(level2 == false){
                    boy.startMoving();
                    dog.startMoving();
                }
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(level2 == false){
                    boy.animation.isMoving = false;
                    girl.animation.isMoving = false;
                }
                if(level2 == true){
                    boy.animation.isMoving = false;
                    dog.animation.isMoving = false;
                }


            }
        });


        Gdx.input.setInputProcessor(stage);
    }

    public World getWorld(){
        return world;
    }

    public TiledMap getMap(){
        return map;
    }



}
