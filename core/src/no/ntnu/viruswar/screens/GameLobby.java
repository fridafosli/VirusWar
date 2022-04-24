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
import no.ntnu.viruswar.services.lobby.LobbyController;

public class GameLobby extends MenuBaseScreen {

    private final Label errorLabel;
    private final LobbyController lobby;
    private final List<String> playerList;

    public GameLobby(final Context context, final LobbyController lobby) {
        super(context);
        this.lobby = lobby;
        this.playerList = new List(skin);
        ScrollPane scrollpane = new ScrollPane(playerList, skin);


        // Initialize labels
        errorLabel = new Label("", skin); // Label that displays no opponents message when playbutton is pressed
        Label pinLabel = new Label("Game Pin: " + lobby.getPin(), skin);
        Label playerLabel = new Label("Players: ", skin);

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
        table.add(scrollpane).width(600).height(400);
        table.row();


        // Set up Back-button
        TextButton backBtn = new TextButton("Leave", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                lobby.removePlayer();
                context.getScreens().pop();
            }
        });
        stage.addActor(backBtn);
        TextButton customizeBtn = new TextButton("Customize", skin);
        customizeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("custom", "clicked");
                context.getScreens().push(new Custom(context, lobby));
            }
        });
        customizeBtn.setPosition((Gdx.graphics.getWidth() - customizeBtn.getWidth()) / 2, 30);
        stage.addActor(customizeBtn);

        // Set up Play-button
        TextButton playBtn = new TextButton("Play", skin);
        playBtn.setPosition(Gdx.graphics.getWidth() - 200, 30);
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lobby.toGame(errorLabel);
            }
        });
        stage.addActor(playBtn);

        // Add all players connected to game to the screen
        playerList.setItems(lobby.getPlayerNames());
        scrollpane.setActor(playerList);
        scrollpane.setScrollingDisabled(true, false);

    }




    @Override
    public void render(float dt) {

        if (lobby.isStarted()) {
            lobby.toGame(errorLabel);
        }

        // Add all players connected to game to the screen
        playerList.clear();



        playerList.setItems(lobby.getPlayerNames());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}