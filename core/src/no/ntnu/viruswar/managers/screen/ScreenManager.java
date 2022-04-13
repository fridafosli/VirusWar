package no.ntnu.viruswar.managers.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.EmptyStackException;
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

    private boolean topIsNotNull() {
        try {
            this.screens.peek();
            return true;
        } catch (EmptyStackException e) {
            return false;
        }
    }

    private Screen getScreen() {
        return this.screens.peek();
    }

    public void nextFrame() {
        if (topIsNotNull()) {
            ScreenUtils.clear(0, 0, 0, 1);
            getScreen().render(Gdx.graphics.getDeltaTime());
        }
    }

    public void push(Screen screen) {
        if (topIsNotNull()) getScreen().hide();
        this.screens.push(screen);
        if (topIsNotNull()) {
            getScreen().show();
        }
    }
}



