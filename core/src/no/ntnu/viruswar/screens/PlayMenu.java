package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.concurrent.TimeUnit;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.data.NetworkDataController;
import no.ntnu.viruswar.services.data.Player;

public class PlayMenu extends MenuBaseScreen {

    public String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";
    private final TextButton backBtn;
    private final TextButton createBtn;
    private final TextButton joinBtn;
    private final TextField pin_input;
    private final TextField nick_input;
    private final TextField host_nick_input;
    private final NetworkDataController playHolder = new NetworkDataController();

    private final NetworkDataController dataHolder = new NetworkDataController();

    protected PlayMenu(final Context context) {
        super(context);

        context.getBackend().setGamePinEventListener(dataHolder);


        pin_input = new TextField("", skin);
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

        // Set up Create button
        createBtn = new TextButton("Create", skin);
        createBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (host_nick_input.getText().length() < 1) {
                    error.setText("Please fill nickname to create game.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                error.setText("");
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
                Player host = new Player(0, 0, 0, "default", host_nick_input.getText());
                context.getBackend().addPlayerToGame(gamePin, host);
                context.getScreens().push(new GameLobby(context, true, gamePin, host));

            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (pin_input.getText().length() < 1 && nick_input.getText().length() < 1) {
                    error.setText("\n Please fill gamepin and nickname to join.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                error.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                if (!dataHolder.activeGamePinsContainsPin(pin_input.getText())) {
                    error.setText("\n Gamepin not valid.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                error.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }

                context.getBackend().setPlayersEventListener(dataHolder, pin_input.getText());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //context.getBackend()
                System.out.println(dataHolder.activeGamePinsContainsPin(pin_input.getText())); // Den finner denne med dataholder
                System.out.println(dataHolder.getPlayers().values().size()); // Den finner denne med dataholder
                //System.out.println(dataHolder.);
                if (playHolder.getPlayers().values().size() > 2) {
                    error.setText("\n Gameroom full.");
                    Thread timer = new Thread() {
                        public void run() {
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                error.setText("");
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
