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
    private TextButton backBtn;
    private TextButton playBtn;
    private DataHolderClass dataHolder = new DataHolderClass();
    private FireBaseInterface _FBIC;
    private ArrayList<String> players = new ArrayList<String>();
    private String playertext = "";
    Label pls;

    protected GameLobby(final GameStateManager gsm, boolean host, String pin) {
        super(gsm);
        this.host = host;
        this.pin = pin;
        _FBIC = gsm.get_FBIC();
        _FBIC.setPlayersEventListener(this.dataHolder, "abc123");


        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                gsm.pop();
            }
        });
        stage.addActor(backBtn);

        // Set up button that displays no player message
        final Label no_pls = new Label("", skin);
        table.add(no_pls);
        table.row();

        Label l = new Label("Game Pin: " + this.pin, skin);
        l.setPosition(100, 20);
        table.add(l);
        table.row();

        Label p = new Label("Players: ", skin);
        p.setPosition(100, 50);
        table.add(p);
        table.row();

        // Set up label that displays connected players
        this.pls = new Label(playertext + "", skin);
        table.add(pls);
        table.row();



        // Set up Play-button
        playBtn = new TextButton("Play", skin);
        playBtn.setPosition(Gdx.graphics.getWidth() - 200, 50);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (dataHolder.getPlayers().values().isEmpty()) {
                    no_pls.setText("Cannot start game without players");
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


        // Add all players connected to game to the screen
        for (Player pl : dataHolder.getPlayers().values()) {
            playertext += pl.getName() + " \n ";
//            players.add(pl.getName());
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
//        players.clear();
//        for (Player pl : dataHolder.getPlayers().values()) {
//            if (players.contains(pl.getName())) {
//                continue;
//            }
//            players.add(pl.getName());
//            playertext += pl.getName() + " \n ";
//        }
//        String[] displayed = playertext.split("\n");
//        for (String str : displayed) {
//            if (!players.contains(str)) {
//                playertext.replace(str + " \n", "");
//            }
//        }

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