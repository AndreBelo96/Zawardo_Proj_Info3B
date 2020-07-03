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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class WinScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private boolean backtoGame;
    private TextButton button;
    private Stage stage;
    private BitmapFont font;

    public WinScreen(SpriteBatch batch, ClickListener listener){
        this.batch=batch;
        camera=new OrthographicCamera();
        //camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        backtoGame=false;
        TextButton.TextButtonStyle style=new TextButton.TextButtonStyle();
        font=new BitmapFont();
        font.setColor(Color.WHITE);
        style.font=font;
        button=new TextButton("Back to Game",style);
        TextButton falsebutton=new TextButton("You won!",style);
        falsebutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-300,200,100);

        button.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        button.addListener(listener);
        stage=new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(falsebutton);
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch,"You won!",Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        stage.draw();
        batch.end();

    }
}