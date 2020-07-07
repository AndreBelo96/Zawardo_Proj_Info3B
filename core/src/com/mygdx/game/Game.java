package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;

	private GameScreen screen;
	private WinScreen wscreen;
	private PauseMenuScreen menu;

	private static boolean syscall=false;
	public static boolean getCall(){return Game.syscall;}
	public static void setCall(boolean call){Game.syscall=call;}

	private ClickManager[] winsListener=generateListeners();
	private ClickManager[] menuListener=menuListeners();
	private EventListener actionDetector0 = new EventListener() {

		@Override
		public boolean handle(Event event) {
			if(getCall())setScreen(menu);
			else setScreen(wscreen);
			return true;
		}

	};
	private EventListener actionDetector1=new EventListener() {
		@Override
		public boolean handle(Event event) {
			if(getCall()){
				Game.setCall(false);
				setScreen(screen);
			}
			return true;
		}
	};


	@Override
	public void create () {
		batch  = new SpriteBatch();
		screen = new GameScreen(batch,actionDetector0);
		wscreen=new WinScreen(batch,winsListener);
		menu=new PauseMenuScreen(batch,menuListener,actionDetector1);
		setScreen(screen);
		/*
		KeyListener key=new KeyListener() {

			@Override
			public void keyTyped(KeyEvent keyEvent) {
				//No action needed.
			}

			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if(keyEvent.getKeyChar()==Input.Keys.ESCAPE){
					if(getScreen().equals(screen))setScreen(menu);
					if(getScreen().equals(menu))setScreen(screen);
				}
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
				//No action needed.
			}

		};*/
	}

	@Override
	public void render(){
		super.render();
		/*if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			if(getScreen().equals(screen))setScreen(menu);
			if(getScreen().equals(menu))setScreen(screen);
		}*/
	}

	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
		wscreen.dispose();
	}

	private void reloadGame(){
		screen=new GameScreen(batch,actionDetector0);
	}
	private void reloadWin(){
		wscreen=new WinScreen(batch,winsListener);
	}

	private ClickManager[] generateListeners() {
		ClickManager[] temp=new ClickManager[3];
		temp[0]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				reloadGame();
				setScreen(screen);
			}
		};
		temp[1]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Tilemap.mapUpdate();
				reloadGame();
				setScreen(screen);
			}
		};
		temp[2]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.this.dispose();
				Gdx.app.exit();
			}
		};
		return temp;
	}

	private ClickManager[] menuListeners(){
		ClickManager[] temp=new ClickManager[3];
		temp[0]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setScreen(screen);
			}
		};
		temp[1]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				reloadGame();
				setScreen(screen);
			}
		};
		temp[2]=new ClickManager(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.this.dispose();
				Gdx.app.exit();
			}
		};
		return temp;
	}
}