package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player implements Entity{

    private Vector2 pos, worldPos;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> move,idle,error;
    private GameScreen screen;
    private float time, animationIdle_Time, animationMove_Time;
    private boolean bool_move, startAnimIdle,startAnimMove;
    private Texture t;

    public Player(GameScreen screen){
        this.pos = new Vector2((Constant.TILE_WIDHT/2 - Constant.PLAYER_WIDHT/2),(Constant.TILE_HEIGHT/2 - Constant.PLAYER_HEIGHT/2 + Constant.BORDER_HEIGHT));
        this.worldPos = new Vector2(0,0);
        this.screen = screen;
        time = 0;
        animationIdle_Time = 0;
        animationMove_Time = 0;
        startAnimIdle = true;
        startAnimMove = false;
        t = new Texture("Slime.png");
        bool_move = false;
        frames = new Array<TextureRegion>();
        for(int i = 0 ; i < 11; i++)
            frames.add(new TextureRegion(this.screen.getAtlas().findRegion("Slime"), i * 20, 0, 20 ,21));
        idle = new Animation(.1f, frames);
        frames.clear();
        for(int i = 11 ; i < 28; i++)
            frames.add(new TextureRegion(this.screen.getAtlas().findRegion("Slime"), i * 20, 0, 20 ,21));
        move = new Animation(.05f, frames);
        frames.clear();
        for(int i = 0 ; i < 2; i++)
            frames.add(new TextureRegion(this.screen.getAtlas().findRegion("Slime"), i * 20, 0, 20 ,21));
        error = new Animation(.05f, frames);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw( setRegion( time ), pos.x , pos.y );
    }

    @Override
    public void update(float delta) {
        time += delta;

        if(startAnimMove){
            animationMove_Time += delta;
        }

        if(startAnimIdle){
            animationIdle_Time += delta;
        }

    }

    public void move(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            pos.x -= 32;
            pos.y += 12;
            worldPos.x += 1;
            bool_move = true;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            pos.x += 32;
            pos.y -= 12;
            worldPos.x -= 1;
            bool_move = true;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            pos.x -= 32;
            pos.y -= 12;
            worldPos.y -= 1;
            bool_move = true;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            pos.x += 32;
            pos.y += 12;
            worldPos.y += 1;
            bool_move = true;
        }
    }

    public Vector2 getPos(){
        return this.worldPos;
    }

    public TextureRegion setRegion(float dt){
        if(bool_move && animationIdle_Time < 1.2f){
            bool_move = false;
            startAnimMove = true;
            animationIdle_Time = 0;
            return move.getKeyFrame( dt ,true);
        }
        else if(!bool_move && animationMove_Time < 3f){
            animationMove_Time = 0;
            startAnimIdle = true;
            return idle.getKeyFrame( dt ,true);
        }
        return error.getKeyFrame( dt ,true);
    }
}

