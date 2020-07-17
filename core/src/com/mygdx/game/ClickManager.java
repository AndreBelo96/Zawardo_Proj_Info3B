package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClickManager extends ClickListener {


    public ClickManager(){
        super();
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        //super.enter(event, x, y, pointer, fromActor);
        if(fromActor instanceof TextButton){
            TextButton.TextButtonStyle temp = new TextButton.TextButtonStyle();
            BitmapFont font=new BitmapFont(Gdx.files.internal("roboto_black.fnt"));
            font.setColor(Color.WHITE);
            temp.font=font;
            ((TextButton)fromActor).setStyle(temp);
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        //super.exit(event, x, y, pointer, toActor);
        if(toActor instanceof TextButton){
            TextButton.TextButtonStyle temp=new TextButton.TextButtonStyle();
            BitmapFont font=new BitmapFont(Gdx.files.internal("roboto_light.fnt"));
            font.setColor(Color.WHITE);
            temp.font = font;
            ((TextButton)toActor).setStyle(temp);
        }
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
    }




}
