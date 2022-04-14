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
    private Player player;
    private TextButton backBtn;
    private TextButton playBtn;
    private DataHolderClass dataHolder = new DataHolderClass();
    private FireBaseInterface _FBIC;
    private String playertext = "";
    Label pls;

    protected GameLobby(final GameStateManager gsm, final boolean host, final String pin, final Player player) {
        super(gsm);
        this.host = host;
        this.pin = pin;
        this.player = player;
        _FBIC = gsm.get_FBIC();
        _FBIC.setPlayersEventListener(this.dataHolder, this.pin);


        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                // removes player from game
                _FBIC.removePlayerFromGame(pin, player.getId());
                if (host) {
                    // set hostPresent false in db
                }
                gsm.pop();
            }
        });
        stage.addActor(backBtn);

        // Set up button that displays no player message or message to wait for creator to start game
        final Label no_pls = new Label("", skin);
        table.padTop(30);
        table.add(no_pls).padBottom(30);
        table.row();
        if (!host) {
            no_pls.setText("Wait for game creator to start the game.");
        }

        Label l = new Label("Game Pin: " + this.pin, skin);
        l.setPosition(100, 20);
        table.add(l).padBottom(30);
        table.row();

        Label p = new Label("Players: ", skin);
        p.setPosition(100, 50);
        table.add(p).padBottom(30);
        table.row();

        // Set up label that displays connected players
        this.pls = new Label(playertext + "", skin);
        table.add(pls);
        table.row();


        TextButton customizeBtn = new TextButton("Customize", skin);
        customizeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gsm.push(new Custom(gsm, player, pin));
            }
        });
        customizeBtn.setPosition(200,300);
        stage.addActor(customizeBtn);

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
        if (host) {
            stage.addActor(playBtn);
        }


        // Add all players connected to game to the screen
        for (Player pl : dataHolder.getPlayers().values()) {
            playertext += pl.getName() + " \n ";
        }
        pls.setText(playertext);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        if (!true) { // if hostPresent
            Gdx.app.log("game", "game deleted");
            // When all players are removed the game will be deleted??
            _FBIC.removePlayerFromGame(this.pin, this.player.getId());
            gsm.pop();
        }
        // Add all players connected to game to the screen
        playertext = "";
        System.out.println(dataHolder.getPlayers().values());
        for (Player pl : dataHolder.getPlayers().values()) {
            playertext += pl.getName() + " \n ";
        }
        pls.setText(playertext);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}