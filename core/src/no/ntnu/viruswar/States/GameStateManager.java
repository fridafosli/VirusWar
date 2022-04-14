//package no.ntnu.viruswar.States;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//import java.util.Stack;
//
//public class GameStateManager {
//
//    private Stack<State> states;
//
//    public GameStateManager() {
//        states = new Stack<State>();
//    }
//
//    public void push(State state) {
//        states.push(state);
//    }
//
//    public void pop() {
//        states.pop();
//        Gdx.input.setInputProcessor(states.peek().getInputprosesspr());
//    }
//
//    public void set(State state) {
//        states.pop();
//        states.push(state);
//    }
//
//    public void update(float dt) {
//        states.peek().update(dt);
//    }
//
//    public void render(SpriteBatch batch) {
//        states.peek().render(batch);
//    }
//
//    public State peek() {
//        return this.states.peek();
//    }
//}
//
