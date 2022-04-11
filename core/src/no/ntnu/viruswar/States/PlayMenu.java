package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.DataHolderClass;
import no.ntnu.viruswar.FireBaseInterface;

public class PlayMenu extends StateMenu {

    public String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";
    private TextButton backBtn;
    private TextButton createBtn;
    private TextButton joinBtn;
    private TextField pin_input;
    private TextField nick_input;


    private DataHolderClass dataHolder = new DataHolderClass();
    private FireBaseInterface _FBIC;

    protected PlayMenu(final GameStateManager gsm) {
        super(gsm);
        this._FBIC = gsm.get_FBIC();
        _FBIC.setGamePinEventListener(dataHolder);

        // Create input-fields
        pin_input = new TextField("", skin);
        nick_input = new TextField("", skin);

        // Create labels
        Label label1 = new Label("Create Game:", skin);
        Label label2 = new Label("Join Game By PIN:", skin);
        Label label3 = new Label("Gamepin: ", skin);
        Label label4 = new Label("Nickname: ", skin);
        final Label error = new Label("", skin);


        // Set up Create button
        createBtn = new TextButton("Create", skin);
        createBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("create", "clicked");

                // Generate a random 6 character game-pin
                String gamePin = "";
                for (int i = 0; i < 6; i++) {
                    int ch = (int) Math.floor( Math.random() * VALID_CHARS.length());
                    gamePin += VALID_CHARS.charAt(ch);
                }

                // If gamepin already exists, a new one is created
                while (dataHolder.activeGamePinsContainsPin(gamePin)) {
                    // Generate a random 6 character game-pin
                    gamePin = "";
                    for (int i = 0; i < 6; i++) {
                        int ch = (int) Math.floor( Math.random() * VALID_CHARS.length());
                        gamePin += VALID_CHARS.charAt(ch);
                    }
                }
                System.out.println(gamePin);
                gsm.push(new GameLobby(gsm, true, gamePin));
            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("join", "clicked");
                if (pin_input.getText().length() < 1 && nick_input.getText().length() < 1) {
                    error.setText("Please fill gamepin and nickname to join.");
                    Thread timer = new Thread(){
                        public void run(){
                            try{
                                sleep(5000);
                            } catch (InterruptedException e){
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
                    error.setText("Gamepin not valid.");
                    Thread timer = new Thread(){
                        public void run(){
                            try{
                                sleep(5000);
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            } finally {
                                error.setText("");
                            }
                        }
                    };
                    timer.start();
                    return;
                }
                gsm.push(new GameLobby(gsm, true, pin_input.getText()));
            }
        });

        // Add actors to Table
        table.padTop(30);
        table.add(label1).padBottom(30);
        table.row();
        table.add(createBtn).padBottom(30);
        table.row();
        table.add(label2).padBottom(30);
        table.row();
        table.add(label3, pin_input);
        table.row();
        table.add(label4, nick_input, joinBtn);
        table.row();
        table.add(error).padBottom(30);

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

        // Set the InputProcessor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
