package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Luis on 12/10/2016.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    protected Vector3 position;
    protected Vector3 velocity;
    public Rectangle bounds;

    public TextureRegion getBird() {
        return animation.getFrame();
    }

    protected Texture texture;
    private Animation animation;
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("birdanimation.png");
        animation = new Animation(new TextureRegion(texture),3,0.5f);
        bounds= new Rectangle(position.x,position.y,texture.getWidth()/3,texture.getHeight()/3);
    }

    public void update(float dt){
        animation.update(dt);
        if(position.y>0){
            velocity.add(0,GRAVITY,0);
            velocity.scl(dt);
            position.add(MOVEMENT*dt,velocity.y,0);
            if(position.y<0){
                position.y = 0;
            }
            velocity.scl(1/dt);
            bounds.setPosition(position.x,position.y);
        }
    }

    public void dispose(){
        texture.dispose();
    }

    public void jump(){
        velocity.y = 250;
    }
}
