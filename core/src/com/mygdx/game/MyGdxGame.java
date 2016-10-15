package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.Menu;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
    GameStateManager gsm;
	public static final int WIDTH = 480;
	public static  final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        gsm.push(new Menu(gsm));
        Gdx.gl.glClearColor(1, 0, 0, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
