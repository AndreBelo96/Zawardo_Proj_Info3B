package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PauseMenuScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextButton reloadbutton,exitbutton,continuebutton;
    private Stage stage;
    private BitmapFont font;
    private Actor actor=new Actor();

    public PauseMenuScreen(SpriteBatch batch, ClickManager[] listener, EventListener detector){
        this.batch=batch;
        camera=new OrthographicCamera();
        TextButton.TextButtonStyle style=new TextButton.TextButtonStyle();
        font=new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
        font.setColor(Color.WHITE);
        style.font=font;
        TextButton falsebutton=new TextButton("PRESS ESC TO RESUME",style);
        reloadbutton=new TextButton(Constant.RELOAD_TEXT,style);
        exitbutton=new TextButton(Constant.EXIT_TEXT,style);
        continuebutton=new TextButton("Continue",style);

        falsebutton.setBounds    (Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-200,200,100);
        continuebutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        reloadbutton.setBounds (Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-150,200,100);
        exitbutton.setBounds   (Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-250,200,100);

        continuebutton.addListener(listener[0]);
        reloadbutton.addListener(listener[1]);
        exitbutton.addListener(listener[2]);
        stage=new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(falsebutton);
        stage.addActor(continuebutton);
        stage.addActor(reloadbutton);
        stage.addActor(exitbutton);
        actor.addListener(detector);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        batch.setProjectionMatrix(camera.combined);
        manageActor();
        batch.begin();
        stage.draw();
        batch.end();

    }

    private void manageActor(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            actor.fire(new Event());
        }
    }
}
