package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class PlayMenu extends MenuBaseScreen {

    private final TextButton backBtn;
    private final TextButton createBtn;
    private final TextButton joinBtn;
    private final TextField pin_input;
    private final TextField nick_input;
    private final TextField host_nick_input;
    private final LobbyController controller;


    public PlayMenu(final Context context) {
        super(context);
        this.controller = new LobbyController(context);

        pin_input = new TextField("", skin);
        nick_input = new TextField("", skin);
        host_nick_input = new TextField("", skin);

        // Create labels
        Label label1 = new Label("Create Game:", skin);
        Label label2 = new Label("Join Game By PIN:", skin);
        Label label3 = new Label("Game pin: ", skin);
        Label label4 = new Label("Nickname: ", skin);
        Label label5 = new Label("Nickname: ", skin);
        final Label error = new Label("", skin);

        // Set up Create button
        createBtn = new TextButton("Create", skin);
        createBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("create", "clicked");
                controller.createLobby(host_nick_input.getText(), error);
            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("join", "clicked");
                controller.joinLobby(pin_input.getText(), nick_input.getText(), error);
            }
        });

        // Add actors to Table
        table.padTop(40);
        table.add(label1).padBottom(40);
        table.row();
        table.add(label5, host_nick_input, createBtn).padBottom(40);
        table.row();
        table.add(label2).padBottom(40);
        table.row();
        table.add(label3, pin_input);
        table.row();
        table.add(label4, nick_input, joinBtn).padBottom(40);
        table.row();

        // Error label must be added outside the table to not disturb the textfields.
        error.setPosition(500, 500);
        stage.addActor(error);

        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
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
