package no.ntnu.viruswar.services.screen;

import com.badlogic.gdx.ScreenAdapter;

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
