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
import no.ntnu.viruswar.services.data.NetworkDataController;
import no.ntnu.viruswar.services.data.Player;
import no.ntnu.viruswar.utils.Constants;

public class PlayMenu extends MenuBaseScreen {

    public String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";
    private final TextButton backBtn;
    private final TextButton createBtn;
    private final TextButton joinBtn;
    private final TextField pin_input;
    private final TextField nick_input;


    private final NetworkDataController dataHolder = new NetworkDataController();

    protected PlayMenu(final Context context) {
        super(context);
        context.getBackend().setGamePinEventListener(dataHolder);
        pin_input = new TextField("", skin);
        pin_input.setMaxLength(60);
        nick_input = new TextField("", skin);

        // Create labels
        // Label CreateLabel = new Label("Create Game:", skin);
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
                if (nick_input.getText().length() < 1) {
                    errorLabel.setText("Please fill nickname to create game.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                errorLabel.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                Gdx.app.log("create", "clicked");

                // Generate a random 6 character game-pin
                String gamePin = "";
                for (int i = 0; i < 6; i++) {
                    int ch = (int) Math.floor(Math.random() * VALID_CHARS.length());
                    gamePin += VALID_CHARS.charAt(ch);
                }

                // If gamepin already exists, a new one is created
                while (dataHolder.activeGamePinsContainsPin(gamePin)) {
                    // Generate a random 6 character game-pin
                    gamePin = "";
                    for (int i = 0; i < 6; i++) {
                        int ch = (int) Math.floor(Math.random() * VALID_CHARS.length());
                        gamePin += VALID_CHARS.charAt(ch);
                    }
                }
                System.out.println(gamePin);
                Player host = new Player(0, 0, 0, "default", nick_input.getText());
                context.getBackend().addPlayerToGame(gamePin, host);
                context.getScreens().push(new GameLobby(context, true, gamePin, host));
            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.setColor(Color.RED);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (pin_input.getText().length() < 1 && nick_input.getText().length() < 1) {
                    errorLabel.setText("\n Please fill gamepin and nickname to join.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                errorLabel.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                if (!dataHolder.activeGamePinsContainsPin(pin_input.getText())) {
                    errorLabel.setText("\n Gamepin not valid.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                errorLabel.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                Gdx.app.log("join", "clicked");
                Player player = new Player(0, 0, 0, "blue", nick_input.getText());
                context.getBackend().addPlayerToGame(pin_input.getText(), player);
                context.getScreens().push(new GameLobby(context, false, pin_input.getText(), player));
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
