package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.managers.screen.ScreenManager;

public class GameLobby extends MenuBaseScreen {

    private boolean host;
    private String pin;

    public GameLobby(ScreenManager screenManager) {
        super(screenManager);
        this.host = true;
        this.pin = "pin";
        Label l = new Label("Game Pin: " + this.pin, skin);
        table.add(l);
        Button button = new TextButton("Start", skin);
        button.setPosition(0, Gdx.graphics.getHeight() - 100);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                gsm.push(new GameScreen(gsm));
            }
        });
        stage.addActor(button);
    }

    @Override
    public void render(float dt) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

}
