package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.utils.Constants;

// The screen where the user may join a game or create a new game
public class PlayMenuScreen extends MenuBaseScreen {

    private final TextButton backBtn;
    private final TextButton createBtn;
    private final TextButton joinBtn;
    private final TextField pin_input;
    private final TextField nick_input;
    private boolean open = false;


    private final LobbyController lobby;


    public PlayMenuScreen(final Context context) {
        super(context);

        // Uses mvc controller for gamelobby to send player to gamelobby
        this.lobby = new LobbyController(context);

        // Set up inputfields for nickname and gamepin
        // Zoom on pin_input when user is writing to this, so that the keyboard does not cover it
        pin_input = new TextField("", skin);
        pin_input.setMaxLength(60);
        pin_input.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.align(Align.center | Align.top);
                table.setPosition(0, Constants.SCREEN_HEIGHT_SCALE * 130);
                stage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Gdx.app.log("Clicked", open + "");
                        table.align(Align.center | Align.top);
                        table.setPosition(0, Gdx.graphics.getHeight());
                        stage.removeListener(this);
                        Gdx.input.setOnscreenKeyboardVisible(false);
                        stage.unfocusAll();
                    }
                });
            }
        });

        nick_input = new TextField("", skin);

        // Create labels
        Label pinLabel = new Label("Join Game By PIN: ", skin);
        Label nickLabel = new Label("Nickname: ", skin);
        final Label errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);
        errorLabel.setWidth(Gdx.graphics.getWidth());
        errorLabel.setAlignment(Align.center);

        // Set up Create button
        createBtn = new TextButton("New Game", skin);
        createBtn.setColor(Color.RED);
        createBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("create", "clicked");
                lobby.createLobby(nick_input.getText(), errorLabel);

            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.setColor(Color.RED);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("join", "clicked");
                lobby.joinLobby(pin_input.getText(), nick_input.getText(), errorLabel);
            }
        });

        // Add actors to Table
        table.padTop(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.add(null, nickLabel);
        table.row();
        table.add();
        table.add(nick_input).width(400).fill().padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.row();
        table.add(null, pinLabel);
        table.row();
        table.add();
        table.add(pin_input).width(400).fill();
        table.add(joinBtn);

        table.row().padTop(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.add(null, createBtn).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.row();

        // Error label must be added outside the table to not disturb the textfields.
        errorLabel.setPosition(0, Constants.SCREEN_HEIGHT_SCALE * 10);
        stage.addActor(errorLabel);

        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - backBtn.getHeight());
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                context.getScreens().pop();
            }
        });
        stage.addActor(backBtn);
    }


    @Override
    public void render(float dt) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(dt);
        stage.draw();
    }


}
