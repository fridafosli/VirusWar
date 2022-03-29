package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayMenu extends StateMenu {

    public String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";
    private TextButton backBtn;
    private TextButton createBtn;
    private TextButton joinBtn;

    private TextField input;

    protected PlayMenu(final GameStateManager gsm) {
        super(gsm);
        // Create input-field
        input = new TextField("", skin);

        // Create labels
        Label label1 = new Label("Create Game:", skin);
        Label label2 = new Label("Join Game By PIN:", skin);

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
                System.out.println(gamePin);
                // TODO: Check if pin already exists.
                gsm.push(new GameLobby(gsm, true, gamePin));
            }
        });

        // Set up Join button
        joinBtn = new TextButton("Join", skin);
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("join", "cl(icked");
                // TODO: check if valid pin. Send to GameLobby
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
        table.add(input, joinBtn);

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
