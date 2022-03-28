package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class PlayMenu extends StateMenu {

    private String VALID_CHARS = "abcdefghijklmnopqrstuvw0123456789";
    private TextButton backBtn;
    private TextButton createBtn;
    private TextButton joinBtn;

    private TextField input;

    protected PlayMenu(final GameStateManager gsm) {
        super(gsm);
        input = new TextField("", skin);
        Label label1 = new Label("Create Game:", skin);
        Label label2 = new Label("Join Game By PIN:", skin);
        createBtn = new TextButton("Create", skin);
        joinBtn = new TextButton("Join", skin);

        table.padTop(30);
        table.add(label1).padBottom(30);
        table.row();
        table.add(createBtn).padBottom(30);
        table.row();
        table.add(label2).padBottom(30);
        table.row();
        table.add(input, joinBtn);

        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                gsm.pop();
            }
        });
        createBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("create", "clicked");
                String gamePin = "";
                for (int i = 0; i < 6; i++) {
                    int ch = (int) Math.floor( Math.random() * VALID_CHARS.length());
                    gamePin += VALID_CHARS.charAt(ch);
                }
                System.out.println(gamePin);
                gsm.push(new GameLobby(gsm, true, gamePin));
            }
        });
        joinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("join", "cl(icked");
                // TODO: check if valid pin. Send to GameLobby
                // gsm.pop();
            }
        });

        stage.addActor(backBtn);
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
