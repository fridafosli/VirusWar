package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.Data.Player;

public class EndScreen extends StateMenu {


    public EndScreen(final GameStateManager gsm, final Player player, boolean winner) {
        super(gsm);

        String text;
        if (winner) {
            text = "WINNER";
        }
        else {
            text = "You died loser ";
        }

        // Initialize labels
        Label dieLabel = new Label(text, skin);
        Label scoreLabel = new Label("Score: " + player.getPoints(), skin);


        // Add the labels to the stage
        dieLabel.setPosition(780, 700);
        scoreLabel.setPosition(780, 550);
        stage.addActor(dieLabel);
        stage.addActor(scoreLabel);


        // Set up Back-button, pops the user back to main menu
        TextButton backBtn = new TextButton("Main Menu", skin);
        backBtn.setPosition(780, 400);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back to main menu", "clicked");
                gsm.pop();
                gsm.pop();
                gsm.pop();
                gsm.pop();
            }
        });
        stage.addActor(backBtn);




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
