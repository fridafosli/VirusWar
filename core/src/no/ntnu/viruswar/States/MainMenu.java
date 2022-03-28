package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.VirusWar;

public class MainMenu extends StateMenu {

    private TextButton playBtn;
    private TextButton tutorialBtn;
    private TextButton settingsBtn;


    public MainMenu(final GameStateManager gsm) {
        super(gsm);
        playBtn = new TextButton("Play", skin);
        playBtn.setHeight(Gdx.graphics.getHeight());
        tutorialBtn = new TextButton("Tutorial", skin);
        settingsBtn = new TextButton("Settings", skin);

        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                gsm.push(new Settings(gsm));

            }
        });
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("play", "clicked");
                gsm.push(new PlayMenu(gsm));

            }
        });
        table.padTop(scale*100);
        table.add(playBtn).padBottom(scale*50);
        table.row();
        table.add(tutorialBtn).padBottom(scale*50);
        table.row();
        table.add(settingsBtn);

//        stage.addActor(table);
//        Gdx.input.setInputProcessor(stage);
//
//        background = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
//        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    public void render(SpriteBatch sb) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();



    }

    @Override
    public void dispose() {

    }

}
