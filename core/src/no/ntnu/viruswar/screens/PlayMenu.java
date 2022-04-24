package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.utils.Constants;

public class PlayMenu extends MenuBaseScreen {

    private final TextButton backBtn;
    private final TextButton createBtn;
    private final TextButton joinBtn;
    private final TextField pin_input;
    private final TextField nick_input;
    private final TextField host_nick_input;


    private final LobbyController lobby;


    public PlayMenu(final Context context) {
        super(context);

        this.lobby = new LobbyController(context);

        pin_input = new TextField("", skin);
        pin_input.setMaxLength(60);
        nick_input = new TextField("", skin);
        host_nick_input = new TextField("", skin);
        final String testPin= pin_input.getText();

        // Create labels
        Label label1 = new Label("Create Game:", skin);
        Label label2 = new Label("Join Game By PIN:", skin);
        Label label3 = new Label("Game pin: ", skin);
        Label label4 = new Label("Nickname: ", skin);
        Label label5 = new Label("Nickname: ", skin);
        final Label error = new Label("", skin);

        // Create labels
        // Label CreateLabel = new Label("Create Game:", skin);c
        // Label joinLabel = new Label("Join Game By PIN:", skin);
        Label pinLabel = new Label("Join Game By PIN: ", skin);
        Label nickLabel = new Label("Nickname: ", skin);
        final Label errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

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
        HorizontalGroup nickGroup = new HorizontalGroup();
        nickGroup.addActor(nickLabel);
        nickGroup.addActor(nick_input);
        table.add(nickGroup).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.row();
        // table.add(CreateLabel).padBottom(Constants.SCREEN_SCALE);
        table.row();
        table.add(createBtn).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.row();
        // table.add(joinLabel).padBottom(Constants.SCREEN_SCALE);
        table.row();
        table.add(pinLabel);
        table.row();
        HorizontalGroup joinGroup = new HorizontalGroup();
        joinGroup.addActor(pin_input);
        joinGroup.addActor(joinBtn);
        table.add(joinGroup).padBottom(Constants.SCREEN_HEIGHT_SCALE * 10);
        table.row();
        table.add(errorLabel);

//        // Error label must be added outside the table to not disturb the textfields.
//        errorLabel.setPosition(500, 500);
//        stage.addActor(errorLabel);

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
