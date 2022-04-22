package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.backend.model.Player;


public class EndScreen extends MenuBaseScreen {
    private Sprite background;

    public EndScreen(final Context context, String gamePin, final Player player, boolean winner) {
        super(context);

        String text;
        if (winner) {
            text = "WINNER";
            context.getBackend().removeGame(gamePin);
            background = new Sprite(new Texture(Gdx.files.internal("winScreen.png")));
        }
        else {

            background = new Sprite(new Texture(Gdx.files.internal("loseScreen.png")));
        }

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        // Initialize labels

        Label scoreLabel = new Label("Score: " + player.getPoints(), skin);


        // Add the labels to the stage
        //dieLabel.setPosition(780, 700);
        scoreLabel.setPosition(780, 550);
        //stage.addActor(dieLabel);
        stage.addActor(scoreLabel);


        // Set up Back-button, pops the user back to main menu
        TextButton backBtn = new TextButton("Main Menu", skin);
        backBtn.setPosition(780, 400);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back to main menu", "clicked");
                while (!(context.getScreens().peek() instanceof MainMenu)) {
                    context.getScreens().pop();
                }
            }
        });
        stage.addActor(backBtn);

    }
/*
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }
*/


    @Override
    public void render(float dt) {
        //context.getBatch().begin();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        SpriteBatch sb= context.getBatch();
        stage.act(dt);
        sb.begin();
        background.draw(sb);
        sb.end();

        stage.draw();


    }

    @Override
    public void dispose() {

    }
}
