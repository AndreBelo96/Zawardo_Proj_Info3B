package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Game extends com.badlogic.gdx.Game {
	private SpriteBatch batch;
	private GameScreen screen;

	private WinScreen wscreen;
	private ClickManager[] listener=generateListeners();

	private EventListener winDetector = new EventListener() {

		@Override
		public boolean handle(Event event) {
			setScreen(wscreen);
			return true;
		}

	};


	@Override
	public void create () {
		batch  = new SpriteBatch();
		screen = new GameScreen(batch,winDetector);
		wscreen=new WinScreen(batch,listener);
		setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		screen.dispose();
		wscreen.dispose();
	}

	private void reloadGame(){
		screen=new GameScreen(batch,winDetector);
	}
	private void reloadWin(){
		wscreen=new WinScreen(batch,listener);
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
				//Game.this.dispose();
			}
		};
		return temp;
	}
}