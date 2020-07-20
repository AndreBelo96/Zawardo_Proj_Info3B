package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;


public class GameScreen extends ScreenAdapter {
    public final Game game;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Tilemap map;
    public static Player player;
    private boolean bool_switch,bool_win, bool_lose;
    private static TextureAtlas atlas;
    private Actor actor = new Actor();

    public GameScreen(Game game){
        this.game = game;
        atlas = new TextureAtlas("Asset_Proj.pack");
        camera = new OrthographicCamera(320,160);
        camera.position.set(Constant.TILE_WIDHT/2,Constant.TILE_HEIGHT/2,0);
        player = new Player(this);
        map = new Tilemap();
        bool_switch = false;
        bool_win = false;
        bool_lose = true;

    }

    public void render(float delta){
        //pulisco lo screen
        Gdx.gl.glClearColor(0.5f,0.3f,0.3f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // disegno sulla camera
        game.batch.setProjectionMatrix(camera.combined);
        cameraInput();


        camera.update();
        player.update(delta);

        for(Tile t : map.base) {
            if (player.getPos().x == t.tileMapPos.y && player.getPos().y == t.tileMapPos.x && bool_switch) {
                t.on = !t.on;
                bool_switch = false;
            }
        }
        game.batch.begin();
        map.render(game.batch);
        player.render(game.batch);
        game.batch.end();

        for(Tile t : map.base) {
            if (!t.on) {
                bool_win = false;
                break;
            }
            bool_win = true;
        }

        for(Tile t : map.base) {
            if(player.getPos().x == t.tileMapPos.y && player.getPos().y == t.tileMapPos.x){
                bool_lose = false;
                break;
            }
        }

        if(bool_win){
            System.out.println("YOU WIN");
            actor.fire(new Event());
        }

        if(bool_lose){
            System.out.println("YOU LOSE");
        }

    }

    public void setBool_switch(boolean bool_switch) {
        this.bool_switch = bool_switch;
    }

    public boolean isBool_switch() {
        return bool_switch;
    }

    public void cameraInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){

            actor.fire(new Event());}
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && player.isCanIJump()){
            camera.position.y -= 12;
            camera.position.x -= 32;
            player.move();
            bool_lose = true;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.D) && player.isCanIJump()){
            camera.position.y += 12;
            camera.position.x += 32;
            player.move();
            bool_lose = true;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.W) && player.isCanIJump()){
            camera.position.y += 12;
            camera.position.x -= 32;
            player.move();
            bool_lose = true;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.S) && player.isCanIJump()){
            camera.position.y -= 12;
            camera.position.x += 32;
            player.move();
            bool_lose = true;
        } else if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            camera.zoom -= 0.005;
        } else if(Gdx.input.isKeyPressed(Input.Keys.X)){
            camera.zoom += 0.005;
        }
    }

    public static Player getPlayer(){
        return player;
    }

    /*@Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(width/2f, height/2f, 0); //by default camera position on (0,0,0)
    }*/

    public void dispose() {
        super.dispose();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
    public void setTileMap(Tilemap map){this.map=map;}
}

