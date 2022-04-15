package no.ntnu.viruswar.services.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Stack;

public class ScreenManager {

    private final Stack<Screen> screens;
    private final SpriteBatch batch;

    public ScreenManager() {
        this.screens = new Stack<>();
        this.batch = new SpriteBatch();
    }

    public SpriteBatch getBatch() {
        return batch;
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

    public void push(Screen screen) {
        if (isNotEmpty()) getScreen().hide();
        this.screens.push(screen);
        if (isNotEmpty()) {
            getScreen().show();
        }
    }

    public void pop() {
        if (isNotEmpty()) {
            Screen old = screens.pop();
            if (isNotEmpty()) {
                getScreen().show();
                old.hide();
            } else {
                screens.push(old);
            }
        }
    }
}



