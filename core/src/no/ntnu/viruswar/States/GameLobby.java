package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameLobby extends StateMenu {

    private boolean host;
    private String pin;

    protected GameLobby(final GameStateManager gsm, boolean host, String pin) {
        super(gsm);
        this.host = host;
        this.pin = pin;

        Label l = new Label("Game Pin: " + this.pin, skin);
        table.add(l);



    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
