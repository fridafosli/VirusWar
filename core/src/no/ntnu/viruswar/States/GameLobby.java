package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import no.ntnu.viruswar.CoreInterfaceClass;
import no.ntnu.viruswar.Data.Player;
import no.ntnu.viruswar.DataHolderClass;
import no.ntnu.viruswar.FireBaseInterface;

public class GameLobby extends StateMenu {

    private boolean host;
    private String pin;
    private final Player player;
    private TextButton backBtn;
    private TextButton playBtn;
    private DataHolderClass dataHolder = new DataHolderClass();
    private FireBaseInterface _FBIC;
    private String playertext = "";
    private Label playerDisplay;

    protected GameLobby(final GameStateManager gsm, final boolean host, final String pin, final Player player) {
        super(gsm);
        this.host = host;
        this.pin = pin;
        this.player = player;
        _FBIC = gsm.get_FBIC();
        _FBIC.setPlayersEventListener(this.dataHolder, this.pin);

        // Initialize labels
        final Label no_pls = new Label("", skin); // Label that displays no opponents message when playbutton is pressed
        Label pinLabel = new Label("Game Pin: " + this.pin, skin);
        Label playerLabel = new Label("Players: ", skin);
        this.playerDisplay = new Label(playertext + "", skin);

        // Add the labels to the table
        table.padTop(30);
        table.add(no_pls).padBottom(30);
        table.row();
        pinLabel.setPosition(100, 20);
        table.add(pinLabel).padBottom(30);
        table.row();
        playerLabel.setPosition(100, 50);
        table.add(playerLabel).padBottom(30);
        table.row();
        table.add(playerDisplay);
        table.row();


        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                // removes player from game
                _FBIC.removePlayerFromGame(pin, player.getId());
                gsm.pop();
            }
        });
        stage.addActor(backBtn);


        // Set up Play-button
        playBtn = new TextButton("Play", skin);
        playBtn.setPosition(Gdx.graphics.getWidth() - 200, 50);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (dataHolder.getPlayers().values().size() < 2) {
                    no_pls.setText("Cannot start game without opponents");
                    // Set timer and make the text disappear after 5 seconds
                    Thread timer = new Thread(){
                        public void run(){
                            try{
                                sleep(5000);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            } finally {
                                no_pls.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                Gdx.app.log("play", "clicked");
                gsm.pop();
            }
        });
        stage.addActor(playBtn);


        // Legge inn en scrollpane?? (Container<Slider>)
        // Add all players connected to game to the screen
        for (Player pl : dataHolder.getPlayers().values()) {
            playertext += pl.getName() + " \n ";
        }
        playerDisplay.setText(playertext);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        // Add all players connected to game to the screen
        playertext = "";
        System.out.println(dataHolder.getPlayers().values());
        for (Player pl : dataHolder.getPlayers().values()) {
            playertext += pl.getName() + " \n ";
        }
        playerDisplay.setText(playertext);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}