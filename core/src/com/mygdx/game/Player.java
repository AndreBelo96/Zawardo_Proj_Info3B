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

    public  enum State { IDLE , JUMPING};
    private State currentState, previousState;
    private Vector2 pos, worldPos;
    private Array<TextureRegion> frames;
    private Animation<TextureRegion> move,idle;
    private GameScreen screen;
    private float time,  animationMove_Time;
    private boolean bool_move,startAnimMove;
    private int wasd;

    public Player(GameScreen screen){
        this.pos = new Vector2(Constant.PLAYER_INITIAL_X,Constant.PLAYER_INITIAL_Y);
        this.worldPos = new Vector2(0,0);
        this.screen = screen;
        time = 0;
        wasd = 0;
        animationMove_Time = 0;
        currentState = State.IDLE;
        previousState = State.IDLE;
        startAnimMove = false;
        bool_move = false;
        frames = new Array<TextureRegion>();
        for(int i = 0 ; i < 11; i++)
            frames.add(new TextureRegion(this.screen.getAtlas().findRegion("Slime"), i * 20, 0, 20 ,21));
        idle = new Animation(.2f, frames, Animation.PlayMode.LOOP);
        frames.clear();
        for(int i = 11 ; i < 28; i++)
            frames.add(new TextureRegion(this.screen.getAtlas().findRegion("Slime"), i * 20, 0, 20 ,21));
        move = new Animation(.1f, frames, Animation.PlayMode.NORMAL);
        frames.clear();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(setRegion( time ), pos.x , pos.y );
    }

    @Override
    public void update(float delta) {
        time = currentState == previousState ? time + delta : 0;

        if(startAnimMove){
            animationMove_Time += delta;
        }

        /*while( <= Constant.MOVE_TIME){
            switch //come lo uso?
            {
                pos.x -= Constant.MOVE_VEL_PER_PIXEL_X;
                pos.y += Constant.PLAYER_MOVEMENT_Y;
            }
        }*/

    }

    public void move(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            bool_move = true;
            currentState = State.JUMPING;
            pos.x -= Constant.TILE_WIDHT/2;
            pos.y += Constant.PLAYER_MOVEMENT_Y;
            wasd = 1;
            worldPos.x += 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            pos.x += Constant.TILE_WIDHT/2;
            pos.y -= Constant.PLAYER_MOVEMENT_Y;
            worldPos.x -= 1;
            bool_move = true;
            currentState = State.JUMPING;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            pos.x -= Constant.TILE_WIDHT/2;
            pos.y -= Constant.PLAYER_MOVEMENT_Y;
            worldPos.y -= 1;
            bool_move = true;
            currentState = State.JUMPING;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            pos.x += Constant.TILE_WIDHT/2;
            pos.y += Constant.PLAYER_MOVEMENT_Y;
            worldPos.y += 1;
            bool_move = true;
            currentState = State.JUMPING;
        }
    }

    public Vector2 getPos(){
        return this.worldPos;
    }

    public TextureRegion setRegion(float dt){
        if(bool_move){
            if(animationMove_Time >= Constant.MOVE_TIME){
                bool_move = false;
                previousState = State.JUMPING;
                currentState = State.IDLE;
            }
            previousState = State.JUMPING;
            startAnimMove = true;
            return move.getKeyFrame( dt,true );
        }
        else {
            animationMove_Time = 0;
            startAnimMove = false;
            previousState = State.IDLE;
            return idle.getKeyFrame( dt ,true);
        }

    }
}

