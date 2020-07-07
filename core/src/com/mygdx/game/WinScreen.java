package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class WinScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextButton reloadbutton,exitbutton,nextbutton;
    private Stage stage;
    private BitmapFont font;

    public WinScreen(SpriteBatch batch, ClickListener listener){
        this.batch=batch;
        camera=new OrthographicCamera();
        //camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        TextButton.TextButtonStyle style=new TextButton.TextButtonStyle();
        font=new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
        font.setColor(Color.WHITE);
        style.font=font;
        reloadbutton=new TextButton("Reload level",style);
        nextbutton=new TextButton("Next Level",style);
        exitbutton=new TextButton("EXIT GAME",style);
        TextButton falsebutton=new TextButton("LEVEL CLEARED",style);
        falsebutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-200,200,100);
        reloadbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        exitbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-250,200,100);
        nextbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-150,200,100);
        reloadbutton.addListener(listener);
        stage=new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(falsebutton);
        stage.addActor(reloadbutton);
        stage.addActor(exitbutton);
        stage.addActor(nextbutton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        stage.draw();
        batch.end();

    }
}