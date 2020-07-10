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
    private float time,  animationMove_Time, Time_Control_Vel;
    private boolean bool_move, startAnimMove, canIJump;
    private int wasd;

    public Player(GameScreen screen){
        this.pos = new Vector2(Constant.PLAYER_INITIAL_X,Constant.PLAYER_INITIAL_Y);
        this.worldPos = new Vector2(0,0);
        this.screen = screen;
        time = 0;
        wasd = 0;
        canIJump = true;
        Time_Control_Vel = 0;
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
        move = new Animation(.05f, frames, Animation.PlayMode.NORMAL);
        frames.clear();
    }

    @Override
    public void render(SpriteBatch batch) {batch.draw(setRegion(time),pos.x,pos.y);}

    @Override
    public void update(float delta) {
        time = currentState == previousState ? time + delta : 0;

        Time_Control_Vel += delta;

        if(startAnimMove){
            animationMove_Time += delta;
        }

        //System.out.println("TIMECONTROL: " + Time_Control_Vel + " mjillisecondi: ");



        if( Time_Control_Vel <= (Constant.MOVE_TIME - 0.3f) && Time_Control_Vel >= 0.3f && bool_move){
            switch(wasd){
                case 1:
                    pos.x -= Constant.MOVE_VEL_PER_PIXEL_X;
                    pos.y += Constant.MOVE_VEL_PER_PIXEL_Y;
                    break;
                case 2:
                    pos.x += Constant.MOVE_VEL_PER_PIXEL_X;
                    pos.y -= Constant.MOVE_VEL_PER_PIXEL_Y;
                    break;
                case 3:
                    pos.x -= Constant.MOVE_VEL_PER_PIXEL_X;
                    pos.y -= Constant.MOVE_VEL_PER_PIXEL_Y;
                    break;
                case 4:
                    pos.x += Constant.MOVE_VEL_PER_PIXEL_X;
                    pos.y += Constant.MOVE_VEL_PER_PIXEL_Y;
                    break;
                default:
            }
        }

    }

    public void move(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && canIJump &&!currentState.equals(State.JUMPING)){
            bool_move = true;
            canIJump = false;
            Time_Control_Vel = 0;
            wasd = 1;
            worldPos.x += 1;
            currentState = State.JUMPING;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S) && canIJump &&!currentState.equals(State.JUMPING)){
            bool_move = true;
            canIJump = false;
            Time_Control_Vel = 0;
            wasd = 2;
            worldPos.x -= 1;
            currentState = State.JUMPING;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && canIJump &&!currentState.equals(State.JUMPING)){
            bool_move = true;
            canIJump = false;
            Time_Control_Vel = 0;
            wasd = 3;
            worldPos.y -= 1;
            currentState = State.JUMPING;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && canIJump &&!currentState.equals(State.JUMPING)){
            bool_move = true;
            canIJump = false;
            Time_Control_Vel = 0;
            worldPos.y += 1;
            wasd = 4;
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
                canIJump = true;
                screen.setBool_switch(true);
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

    public boolean isCanIJump() {
        return canIJump;
    }

}

