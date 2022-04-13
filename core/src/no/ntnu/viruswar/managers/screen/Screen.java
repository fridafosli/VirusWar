package no.ntnu.viruswar.managers.screen;

import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.viruswar.managers.screen.ScreenManager;

public abstract class Screen extends ScreenAdapter {
    protected ScreenManager gsm;

    protected Screen(ScreenManager gsm) {
        this.gsm = gsm;
    }

    public abstract void show();
    public abstract void render(float dt);
    public void hide() {};
    public void dispose() {};

}
