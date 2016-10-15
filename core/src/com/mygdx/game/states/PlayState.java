package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Pipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis on 12/10/2016.
 */

public class PlayState extends State {
    private Bird bird;
    private Texture background;
    private Vector2 groundPosition1,groundPosition2;
    private Texture groundTexture;
    private List<Pipe> pipes;
    private static final int PIPE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -100;
    private static final int PIPE_SPACING = 125;
    private float score;
    public PlayState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, MyGdxGame.WIDTH/2,MyGdxGame.HEIGHT/2);
        background = new Texture("bg.png");
        bird = new Bird(50,100);
        groundTexture = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPosition2 = new Vector2((cam.position.x -cam.viewportWidth/2)-groundTexture.getWidth(),GROUND_Y_OFFSET);
        pipes = new ArrayList<Pipe>();
        for (int i = 1; i <=PIPE_COUNT; i++) {
            pipes.add(new Pipe(i*(PIPE_SPACING + Pipe.PIPE_WIDTH)));
        }
    }
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for (Pipe pipe: pipes) {
            if(cam.position.x - (cam.viewportWidth/2)>pipe.getTopPostion().x + pipe.getTop().getWidth()){
                pipe.reposition(pipe.getTopPostion().x + (Pipe.PIPE_WIDTH + PIPE_SPACING)*PIPE_COUNT);
            }
            if(bird.getPosition().x >=pipe.getTopPostion().x && !pipe.isHit){
                pipe.isHit = true;
                score+=0.5f;
                System.out.println("Score "+score);
            }
            if(pipe.collides(bird.bounds)){
                gsm.set(new PlayState(gsm));
            }
        }
        if(bird.getPosition().y<=groundTexture.getHeight() + groundPosition1.y){
            gsm.set(new PlayState(gsm));
        }
        cam.update();
        updateGround();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(background,cam.position.x - (cam.viewportWidth/2),0);
        batch.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for (Pipe pipe:pipes) {
            batch.draw(pipe.getBottom(), pipe.getBottomPosition().x,pipe.getBottomPosition().y);
            batch.draw(pipe.getTop(), pipe.getTopPostion().x,pipe.getTopPostion().y);
        }
        batch.draw(groundTexture,groundPosition1.x,groundPosition1.y);
        batch.draw(groundTexture,groundPosition2.x,groundPosition2.y);
        batch.end();
    }

    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2)>groundPosition1.x + groundTexture.getWidth()){
            groundPosition1.add(groundTexture.getWidth()*2,0);
        }
        if(cam.position.x - (cam.viewportWidth/2)>groundPosition2.x + groundTexture.getWidth()){
            groundPosition2.add(groundTexture.getWidth()*2,0);
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        groundTexture.dispose();
        for (Pipe pipe:pipes) {
            pipe.dispose();
        }
    }
}
