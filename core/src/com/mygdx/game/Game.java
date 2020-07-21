package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends com.badlogic.gdx.Game {
	public static SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new LoginScreen(this));
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
	}
}