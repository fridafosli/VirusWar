package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.models.Player;
import no.ntnu.viruswar.utils.Constants;


public class EndScreen extends MenuBaseScreen {
    private Sprite background;
    private OrthographicCamera cam = new OrthographicCamera();

    public EndScreen(final Context context, String gamePin, final Player player, boolean winner) {
        super(context);

        if (winner) {
            background = new Sprite(new Texture(Gdx.files.internal("winScreen.png")));
            context.getBackend().removeGame(gamePin);
        } else {
            background = new Sprite(new Texture(Gdx.files.internal("loseScreen.png")));
        }
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Initialize labels
        Label scoreLabel = new Label("Score: " + player.getPoints(), skin);


        // Add the labels to the stage
        scoreLabel.setPosition(Gdx.graphics.getWidth() / 2f - scoreLabel.getWidth() / 2f, Constants.SCREEN_HEIGHT_SCALE * 55);
        stage.addActor(scoreLabel);

        // Set up Back-button, pops the user back to main menu
        TextButton backBtn = new TextButton("Main Menu", skin);
        backBtn.setPosition(Gdx.graphics.getWidth() / 2f - backBtn.getWidth() / 2f, Constants.SCREEN_HEIGHT_SCALE * 45);
        backBtn.setColor(Color.RED);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back to main menu", "clicked");
                while (!(context.getScreens().peek() instanceof MainMenuScreen)) {
                    context.getScreens().pop();
                }

            }
        });
        stage.addActor(backBtn);
    }


    @Override
    public void render(float dt) {
        cam.setToOrtho(false);
        context.getBatch().setProjectionMatrix(cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.act(dt);
        context.getBatch().begin();
        background.draw(context.getBatch());
        context.getBatch().end();

        stage.draw();

    }

    @Override
    public void dispose() {

    }
}