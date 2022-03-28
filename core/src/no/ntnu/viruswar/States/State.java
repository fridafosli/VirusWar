package no.ntnu.viruswar.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State{
    protected GameStateManager gsm;
    protected float scale;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        scale = Gdx.graphics.getHeight()/300;

    }

    protected abstract InputProcessor getInputprosesspr();
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}

