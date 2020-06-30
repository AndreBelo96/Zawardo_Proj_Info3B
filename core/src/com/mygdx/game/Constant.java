package com.mygdx.game;

public class Constant {
    public static final int TILE_WIDHT = 64;
    public static final int TILE_HEIGHT = 32;
    public static final int PLAYER_WIDHT = 20;
    public static final int PLAYER_HEIGHT = 21;
    public static final int BORDER_HEIGHT = 4;
    public static final float IDLE_TIME =  11f * 0.2f;
    public static final float MOVE_TIME =  1.7f;//17f * 0.1f;
    public static final float FLY_TIME =  1.1f;//17f * 0.1f;
    public static final float PLAYER_INITIAL_X = TILE_WIDHT/2 - PLAYER_WIDHT/2;
    public static final float PLAYER_INITIAL_Y = TILE_HEIGHT/2 - PLAYER_HEIGHT/2 + BORDER_HEIGHT;
    public static final float PLAYER_MOVEMENT_Y = TILE_HEIGHT/2 - BORDER_HEIGHT;
    public static final float MOVE_VEL_PER_PIXEL_X = (TILE_WIDHT/2) / 66f;
    public static final float MOVE_VEL_PER_PIXEL_Y = (PLAYER_MOVEMENT_Y) / 66f;
}
