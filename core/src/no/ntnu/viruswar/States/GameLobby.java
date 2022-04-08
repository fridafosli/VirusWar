package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameLobby extends StateMenu {

    private boolean host;
    private String pin;

    protected GameLobby(final GameStateManager gsm, boolean host, String pin) {
        super(gsm);
        this.host = host;
        this.pin = pin;

        Label pinLabel = new Label("Game Pin: " + this.pin, skin);
        table.add(pinLabel);
        TextButton backBtn = new TextButton("Back", skin);
        TextButton customizeBtn = new TextButton("Customize", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Back", "clicked");
                gsm.pop();
            }
        });
        customizeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Custom", "clicked");
                gsm.push(new Custom(gsm));
            }
        });
        customizeBtn.setPosition((Gdx.graphics.getWidth()/2)-40, 10);
        stage.addActor(backBtn);
        stage.addActor(customizeBtn);



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
