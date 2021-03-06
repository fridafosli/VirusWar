package no.ntnu.viruswar.services.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Stack;

/*Defines screen stack, and methods that change the order of the stack, used for buttons such as backbutton*/
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
                old.hide();
                getScreen().show();
            } else {
                screens.push(old);
            }
        }
    }

    public Screen peek() {
        return this.screens.peek();
    }

}



