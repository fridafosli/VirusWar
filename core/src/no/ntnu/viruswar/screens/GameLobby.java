package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.data.NetworkDataController;
import no.ntnu.viruswar.services.data.Player;

public class GameLobby extends MenuBaseScreen {

    private final Player player;
    private final Context context;
    private final boolean host;
    private final String pin;
    private final TextButton backBtn;
    private final TextButton playBtn;
    private final NetworkDataController dataHolder = new NetworkDataController();
    private String playertext = "";
    private final Label playerDisplay;
    private final ScrollPane scrollpane;
    private final List<String> playerlist;

    protected GameLobby(final Context context, final boolean host, final String pin, final Player player) {
        super(context);
        this.context = context;

        this.host = host;
        this.pin = pin;
        this.player = player;
        this.playerlist = new List(skin);
        this.scrollpane = new ScrollPane(playerlist, skin);

        context.getBackend().setPlayersEventListener(this.dataHolder, this.pin);

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
        table.add(scrollpane).width(600).height(700);
        table.row();


        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                // removes player from game
                context.getBackend().removePlayerFromGame(pin, player.getId());
                context.getScreens().pop();
            }
        });
        stage.addActor(backBtn);


        // Set up Play-button
        playBtn = new TextButton("Play", skin);
        playBtn.setPosition(Gdx.graphics.getWidth() - 200, 50);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (dataHolder.getPlayers().values().size() < 1) { // < 1 for testing
                    no_pls.setText("Cannot start game without opponents");
                    // Set timer and make the text disappear after 5 seconds
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
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
                context.getScreens().push(new GameScreen(context));
            }
        });
        stage.addActor(playBtn);


        // Legge inn en scrollpane?? (Container<Slider>)
        // Add all players connected to game to the screen
        playerlist.setItems(dataHolder.getPlayerNames());
        scrollpane.setActor(playerlist);
        scrollpane.setScrollingDisabled(true, false);

    }

    @Override
    public void render(float dt) {
        // Add all players connected to game to the screen
        playerlist.clear();
        playerlist.setItems(dataHolder.getPlayerNames());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}