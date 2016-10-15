package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Luis on 12/10/2016.
 */

public class Pipe {


    private Vector2 topPostion, bottomPosition;
    private Random rand;
    public static final int PIPE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int PIPE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Rectangle boundTop,boundBottom;
    public boolean isHit;
    public Pipe(float x){
        top = new Texture("toptube.png");
        bottom = new Texture("bottomtube.png");
        rand = new Random();
        topPostion = new Vector2(x,rand.nextInt(FLUCTUATION)+ PIPE_GAP + LOWEST_OPENING);
        bottomPosition = new Vector2(x,topPostion.y-PIPE_GAP - bottom.getHeight());
        boundTop = new Rectangle(topPostion.x,topPostion.y,top.getWidth(),top.getHeight());
        boundBottom = new Rectangle(bottomPosition.x,bottomPosition.y,bottom.getWidth(),bottom.getHeight());
    }

    public Texture getTop() {
        return top;
    }

    public Texture getBottom() {
        return bottom;
    }

    private Texture top, bottom;

    public Vector2 getBottomPosition() {
        return bottomPosition;
    }

    public Vector2 getTopPostion() {
        return topPostion;
    }

    public void reposition(float x) {
        isHit = false;
        topPostion = new Vector2(x,rand.nextInt(FLUCTUATION)+ PIPE_GAP + LOWEST_OPENING);
        bottomPosition = new Vector2(x,topPostion.y-PIPE_GAP - bottom.getHeight());
        boundBottom.setPosition(bottomPosition.x,boundBottom.y);
        boundTop.setPosition(topPostion.x,topPostion.y);
    }

    public void dispose(){
        top.dispose();
        bottom.dispose();
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundBottom) || player.overlaps(boundTop);
    }
}

