package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Tilemap {

    //base mappa
    public LinkedList<Tile> base;
    private Texture off;
    private Texture on;
    //oggetti mappa
    public LinkedList<Tile> objects;
    private Texture potenziamento;
    //map
    private String[][] map;
    //map name
    private static String map_prefix="lvl",map_no="0",map_filetype=".txt";

    public Tilemap(){
        /*potenziamento = new Texture("");*/
        base    = new LinkedList<Tile>();
        objects = new LinkedList<Tile>();
        map = new String[10][10];
        try {
            fillMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //controllo = false;
    }

    public void render(SpriteBatch batch){
        for(Tile t : base){
            t.render(batch);
        }

        for(Tile t : objects){
            t.render(batch);
        }
    }

    public void fillMap() throws IOException{
        FileHandle fh = Gdx.files.internal(Tilemap.mapdir());
        BufferedReader br = new BufferedReader(new FileReader(fh.path()));
        String s  = "";
        int count_row = 0;
        int count_col = 0;
        boolean bool = true;

        while((s = br.readLine()) != null){
            map[count_row] = s.split(" ");
            count_row++;
            if(bool){
                count_col = s.length()/2;
                bool = false;
            }
        }
        br.close();

        map[0][0] = "1"; // per mettere il player
        for(int row = count_row-1; row >= 0; row--){
            for(int col = count_col; col >= 0; col--){

                //serve per la visuale isometrica
                float x = (row - col) * Constant.TILE_WIDHT/2.001f;
                float y = (col + row) * Constant.TILE_HEIGHT/2.6f;

                if(map[row][col].equals("1") && (GameScreen.getPlayer().getPos().x == row && GameScreen.getPlayer().getPos().y == col)){
                    base.add(new Tile(new Vector2(row,col), new Vector2(x,y), true));
                }else if (map[row][col].equals("1") && (GameScreen.getPlayer().getPos().x != row || GameScreen.getPlayer().getPos().y != col)){
                    base.add(new Tile(new Vector2(row,col), new Vector2(x,y), false));
                }
            }
        }
    }

    private static String mapdir(){
        return Tilemap.map_prefix+Tilemap.map_no+Tilemap.map_filetype;
    }

    private static void mapUpdate(int nextLevel){
        Tilemap.map_no=(new Integer(nextLevel)).toString();
    }

    public static void mapUpdate(){
        int no=Integer.parseInt(Tilemap.map_no);
        if(no==2)mapUpdate(0);
        else mapUpdate(no+1);
    }
}
