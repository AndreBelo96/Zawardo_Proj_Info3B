package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


import java.util.ArrayList;

public class LoginScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TextButton reloadbutton,exitbutton,nextbutton;
    private Stage stage;
    private final Label name_label, pass_label;
    private BitmapFont font;
    private Actor actor;

    public LoginScreen(SpriteBatch batch, EventListener listener){
        this.batch=batch;
        actor = new Actor();
        actor.addListener(listener);
        camera=new OrthographicCamera();
        //camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        font = new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
        font.setColor(Color.WHITE);
        style.font = font;
        Label.LabelStyle label_style = new Label.LabelStyle(font,Color.WHITE);
        name_label = new Label("Name: ", label_style);
        pass_label = new Label("Password: ", label_style);
        /*TextButton Name_Label = new TextButton(Constant.WIN_TEXT,style);
        TextButton Password_Label = new TextButton(Constant.);*/
        reloadbutton=new TextButton(Constant.RELOAD_TEXT,style);
        nextbutton=new TextButton(Constant.NEXT_TEXT,style);
        exitbutton=new TextButton(Constant.EXIT_TEXT,style);

        name_label.setBounds(Gdx.graphics.getWidth()/2-300,Gdx.graphics.getHeight()-200,200,100);
        pass_label.setBounds(Gdx.graphics.getWidth()/2-300,Gdx.graphics.getHeight()-240,200,100);
        reloadbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        nextbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-150,200,100);
        exitbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-250,200,100);

        /*reloadbutton.addListener(listener.get(0));
        nextbutton.addListener(listener.get(1));
        exitbutton.addListener(listener.get(2));*/

        stage=new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(name_label);
        stage.addActor(pass_label);
        stage.addActor(reloadbutton);
        stage.addActor(nextbutton);
        stage.addActor(exitbutton);
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
