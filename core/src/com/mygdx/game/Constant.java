package com.mygdx.game;

public class Constant {
    public static final int TILE_WIDHT = 64;
    public static final int TILE_HEIGHT = 32;
    public static final int PLAYER_WIDHT = 20;
    public static final int PLAYER_HEIGHT = 21;
    public static final int BORDER_HEIGHT = 4;
    public static final float IDLE_TIME =  10f * 0.2f;
    public static final float MOVE_TIME =  .86f;//17f * 0.1f;
    public static final float FLY_TIME =  .86f;//17f * 0.1f;
    public static final float PLAYER_INITIAL_X = TILE_WIDHT/2 - PLAYER_WIDHT/2;
    public static final float PLAYER_INITIAL_Y = TILE_HEIGHT/2 - PLAYER_HEIGHT/2 + BORDER_HEIGHT;
    public static final float PLAYER_MOVEMENT_Y = TILE_HEIGHT/2 - BORDER_HEIGHT;
    public static final float MOVE_VEL_PER_PIXEL_X = (TILE_WIDHT/2) / 15f;
    public static final float MOVE_VEL_PER_PIXEL_Y = (PLAYER_MOVEMENT_Y) / 15f;
    public static final String WIN_TEXT="LEVEL CLEARED";
    public static final String RELOAD_TEXT="Reload Level";
    public static final String NEXT_TEXT="Next Level";
    public static final String EXIT_TEXT="EXIT GAME";
    public static final int N_OF_LEVELS=4,FIRST_LEVEL=0;

}
