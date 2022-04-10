package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import no.ntnu.viruswar.FireBaseInterface;

public class GameStateManager {

    private Stack<State> states;
    private FireBaseInterface _FBIC;

    public GameStateManager(FireBaseInterface FBIC) {
        states = new Stack<State>();
        this._FBIC = FBIC;
    }

    public void push(State state) {
        states.push(state);
    }

    public FireBaseInterface get_FBIC() { return _FBIC; }

    public void pop() {
        states.pop();
        Gdx.input.setInputProcessor(states.peek().getInputprosesspr());
    }

    public void set(State state) {
        states.pop();
        states.push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }

    public State peek() {
        return this.states.peek();
    }
}

