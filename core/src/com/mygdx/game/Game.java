package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.ArrayList;


public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;
	private PauseMenuScreen menu;
	//public GameCamera cam;





	@Override
	public void create () {
		batch   = new SpriteBatch();
		this.setScreen(new LoginScreen(batch,this));
	}

	@Override
	public void render(){

		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}

	public static void setScreen(GameScreen screen){setScreen(screen);}

}