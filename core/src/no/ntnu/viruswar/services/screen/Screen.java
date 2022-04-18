package no.ntnu.viruswar.services.screen;

import com.badlogic.gdx.ScreenAdapter;

public abstract class Screen extends ScreenAdapter {
    protected ScreenManager sm;

    protected Screen(ScreenManager sm) {
        this.sm = sm;
    }

    public abstract void show();

    public abstract void render(float dt);

    public void hide() {
    }

    public void dispose() {
    }

}
