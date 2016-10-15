package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;


/**
 * Created by Luis on 12/10/2016.
 */

public class Menu extends State {
    private Texture background;
    private Texture playBtn;
    private Vector2 playBtnPosition;
    public Menu(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH/2,MyGdxGame.HEIGHT/2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        playBtnPosition = new Vector2( MyGdxGame.WIDTH/2 -(playBtn.getWidth()/2),MyGdxGame.HEIGHT/2 -(playBtn.getHeight()/2));
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background,0,0);
        batch.draw(playBtn, cam.position.x - (playBtn.getWidth()*0.5f),cam.position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
