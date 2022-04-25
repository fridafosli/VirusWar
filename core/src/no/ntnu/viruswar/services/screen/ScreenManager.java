package no.ntnu.viruswar.services.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Stack;

// Screen manager to keep track of the current state of the game the user has navigated to
// Displayes the state of the screen on top of the stack
public class ScreenManager {

    private final Stack<Screen> screens;

    public ScreenManager() {
        this.screens = new Stack<>();
    }

    private boolean isNotEmpty() {
        return !(screens.isEmpty());
    }

    private Screen getScreen() {
        return this.screens.peek();
    }

    public void nextFrame() {
        if (isNotEmpty()) {
            ScreenUtils.clear(0, 0, 0, 1);
            getScreen().render(Gdx.graphics.getDeltaTime());
        }
    }

    // Go to new state
    public void push(Screen screen) {
        if (isNotEmpty()) getScreen().hide();
        this.screens.push(screen);
        if (isNotEmpty()) {
            getScreen().show();
        }
    }

    // Go to previous state
    public void pop() {
        if (isNotEmpty()) {
            Screen old = screens.pop();
            if (isNotEmpty()) {
                old.hide();
                getScreen().show();
            } else {
                screens.push(old);
            }
        }
    }

    // Return current state
    public Screen peek() {
        return this.screens.peek();
    }

}



