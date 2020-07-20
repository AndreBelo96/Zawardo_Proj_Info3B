package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LoginScreen extends ScreenAdapter {
    public final Game game;

    private SpriteBatch batch;
    private Skin skin;
    private OrthographicCamera camera;
    private TextButton playButton,exitbutton;
    private Stage stage;
    private final Label name_label, pass_label;
    private final TextField nameText, passText;
    private final Preferences prefs;
    private BitmapFont font;

    public LoginScreen( final Game game){

        this.game = game;
        camera=new OrthographicCamera();
        //camera.setToOrtho(false,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        font = new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
        skin = new Skin(Gdx.files.internal("skin.json"));
        font.setColor(Color.WHITE);
        style.font = font;
        prefs = Gdx.app.getPreferences("Zawardo");
        Label.LabelStyle label_style = new Label.LabelStyle(font,Color.WHITE);
        name_label = new Label("Name: ", label_style);
        pass_label = new Label("Password: ", label_style);
        nameText = new TextField(prefs.getString("name", ""), skin);
        passText = new TextField("", skin);
        playButton = new TextButton("Play",style);
        exitbutton = new TextButton(Constant.EXIT_TEXT,style);
        final LoginScreen loginScreen = this;
        stage = new Stage();
        stage.clear();

        name_label.setBounds(Gdx.graphics.getWidth()/2-300,Gdx.graphics.getHeight()-200,200,100);
        pass_label.setBounds(Gdx.graphics.getWidth()/2-300,Gdx.graphics.getHeight()-250,200,100);
        nameText.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-170,200,50);
        passText.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()-220,200,50);
        playButton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        playButton.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        exitbutton.setBounds(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-250,200,100);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("hiii");
                game.setScreen( new GameScreen(game) );
            }
        } );



        stage.addActor(name_label);
        stage.addActor(pass_label);
        stage.addActor(nameText);
        stage.addActor(passText);
        stage.addActor(playButton);
        stage.addActor(exitbutton);

        Gdx.input.setInputProcessor(this.stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.end();

        stage.draw();
        stage.act();
    }
}
