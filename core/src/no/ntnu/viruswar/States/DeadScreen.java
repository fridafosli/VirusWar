package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.Data.Player;
import no.ntnu.viruswar.DataHolderClass;
import no.ntnu.viruswar.FireBaseInterface;

public class DeadScreen extends StateMenu {

    private final Player player;
    private String gamePin;
    private TextButton backBtn;
    private DataHolderClass dataHolder = new DataHolderClass();
    private FireBaseInterface _FBIC;

    public DeadScreen(final GameStateManager gsm, String gamePin, final Player player) {
        super(gsm);
        this.player = player;
        this.gamePin = gamePin;
        _FBIC = gsm.get_FBIC();
        _FBIC.setPlayersEventListener(dataHolder, gamePin);

        // Initialize labels
        Label dieLabel = new Label("You died loser ", skin);
        Label scoreLabel = new Label("Score: " + dataHolder.getPlayers().get(player.getId()).getPoints(), skin);
//        Label scoreLabel = new Label("Score: ", skin);

        // Add the labels to the stage
        dieLabel.setPosition(780, 700);
        scoreLabel.setPosition(780, 550);
        stage.addActor(dieLabel);
        stage.addActor(scoreLabel);

//        table.padTop(30);
//        table.row();
//        pinLabel.setPosition(100, 20);
//        table.add(pinLabel).padBottom(30);
//        table.row();
//        playerLabel.setPosition(100, 50);
//        table.add(playerLabel).padBottom(30);
//        table.row();


        // Set up Back-button, pops the user back to main menu
        backBtn = new TextButton("Main Menu", skin);
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
