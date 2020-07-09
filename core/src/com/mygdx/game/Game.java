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
	private WinScreen wscreen;
	private PauseMenuScreen menu;
	private static boolean syscall=false;

	public static boolean getCall(){return syscall;}
	public static void setCall(boolean call){syscall=call;}

	ArrayList<ClickManager> winsListener=new ArrayList<ClickManager>();
	ArrayList<ClickManager> menuListener=new ArrayList<ClickManager>();
	EventListener actionDetector0 = new EventListener() {
		@Override
		public boolean handle(Event event) {
			if(Game.getCall())setScreen(menu);
			else setScreen(wscreen);
			return true;
		}
	};



	@Override
	public void create () {
		batch   = new SpriteBatch();
		screen  = new GameScreen(batch,actionDetector0);
		winsListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				reloadGame();
				setScreen(screen);
			}
		});
		winsListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Tilemap.mapUpdate();
				reloadGame();
				setScreen(screen);
			}
		});
		winsListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.this.dispose();
				Gdx.app.exit();
			}
		});
		menuListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.setCall(false);
				setScreen(screen);
			}
		});
		menuListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				reloadGame();
				setScreen(screen);
			}
		});
		menuListener.add(new ClickManager(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Game.this.dispose();
				Gdx.app.exit();
			}
		});
		wscreen = new WinScreen(batch,winsListener);
		menu    = new PauseMenuScreen(batch,menuListener);
		setScreen(screen);
	}

	@Override
	public void render(){
		super.render();
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
}