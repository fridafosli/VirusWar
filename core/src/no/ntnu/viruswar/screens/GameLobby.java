package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class GameLobby extends MenuBaseScreen {

    private final TextButton backBtn;
    private final TextButton playBtn;
    private final Label errorLabel;
    private final BackendModel dataHolder = new BackendModel();
    private String playertext = "";
    private final Label playerDisplay;
    private LobbyController controller;

    public GameLobby(final Context context, final LobbyController controller) {
        super(context);
        this.controller = controller;

        // Initialize labels
        errorLabel = new Label("", skin); // Label that displays no opponents message when playbutton is pressed
        Label pinLabel = new Label("Game Pin: " + controller.getPin(), skin);
        Label playerLabel = new Label("Players: ", skin);
        this.playerDisplay = new Label(playertext + "", skin);

        // Add the labels to the table
        table.padTop(30);
        table.add(errorLabel).padBottom(30);
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
                controller.removePlayer();
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
                controller.toGame(errorLabel);
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
    public void render(float dt) {

        if (controller.isStarted()) {
            controller.toGame(errorLabel);
        }

        // Add all players connected to game to the screen
        playertext = "";
        int count = 0;
        for (Player pl : controller.getPlayers().values()) {
            if (count < 1) {
                playertext += pl.getName() + " \t & \t ";
                count++;
            } else {
                playertext += pl.getName() + " \n & \t ";
                count = 0;
            }
        }
        playerDisplay.setText(playertext);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}