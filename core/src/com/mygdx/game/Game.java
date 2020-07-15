package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.ArrayList;


public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;
	private GameScreen screen;
	private LoginScreen loginScreen;
	private WinScreen winScreen;
	private PauseMenuScreen menu;
	private static boolean syscall = false;

	public static boolean getCall(){ return syscall; }
	public static void setCall(boolean call){ syscall = call; }
	public static void reverseState(){ syscall =! syscall; }

	ArrayList<ClickManager> listeners=new ArrayList<ClickManager>();
	EventListener actionDetector0 = new EventListener() {
		@Override
		public boolean handle(Event event) {
			if(Game.getCall())setScreen(menu);
			else setScreen(winScreen);
			return true;
		}
	};



	@Override
	public void create () {
		batch   = new SpriteBatch();
		loginScreen  = new LoginScreen(batch,actionDetector0);
		listeners.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(!Game.getCall())reloadGame();
				else Game.reverseState();
				setScreen(loginScreen);

			}
		});
		listeners.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(!Game.getCall())Tilemap.mapUpdate();
				else Game.reverseState();
				reloadGame();
				setScreen(loginScreen);
			}
		});
		listeners.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.this.dispose();
				Gdx.app.exit();
			}
		});
		winScreen = new WinScreen(batch,listeners);
		menu    = new PauseMenuScreen(batch,listeners);
		setScreen(loginScreen);
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose () {
		//batch.dispose();
		screen.dispose();
		winScreen.dispose();
	}

	private void reloadGame(){
		screen=new GameScreen(batch,actionDetector0);
	}
	private void reloadWin(){
		winScreen =new WinScreen(batch,listeners);
	}
	private void reloadMenu(){menu=new PauseMenuScreen(batch,listeners);}
}