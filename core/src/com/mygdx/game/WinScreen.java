package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class WinScreen extends ScreenAdapter {
    private OrthographicCamera camera;
    private TextButton reloadbutton,exitbutton,nextbutton;
    private Stage stage;
    private BitmapFont font;
    private Game game;

    public WinScreen(final Game game){
        this.game=game;
        camera=new OrthographicCamera();
        TextButton.TextButtonStyle style=new TextButton.TextButtonStyle();
        font=new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
        font.setColor(Color.WHITE);
        style.font=font;
        TextButton falsebutton=new TextButton(Constant.WIN_TEXT,style);
        reloadbutton=new TextButton(Constant.RELOAD_TEXT,style);
        nextbutton=new TextButton(Constant.NEXT_TEXT,style);
        exitbutton=new TextButton(Constant.EXIT_TEXT,style);

        falsebutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-200,200,100);
        reloadbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        nextbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-150,200,100);
        exitbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-250,200,100);

        reloadbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });
        nextbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Tilemap.mapUpdate();
                game.setScreen(new GameScreen(game));
            }
        });
        exitbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage=new Stage();
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(falsebutton);
        stage.addActor(reloadbutton);
        stage.addActor(nextbutton);
        stage.addActor(exitbutton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        stage.draw();
        game.batch.end();

    }
}