package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.utils.Constants;


public class MainMenu extends MenuBaseScreen {

    private final TextButton playBtn;
    private final TextButton tutorialBtn;
    private final TextButton settingsBtn;
    private Sprite background;


    public MainMenu(final Context context) {
        super(context);
        context.getAssets().setMusic(true,1f);
        // Create the play-button
        playBtn = new TextButton("Play", skin);
        playBtn.setHeight(Gdx.graphics.getHeight());
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("play", "clicked");
                context.getScreens().push(new PlayMenu(context));
            }
        });

        // Create the Tutorial-button
        tutorialBtn = new TextButton("Tutorial", skin);
        tutorialBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("tutorial", "clicked");
                sm.push(new Tutorial(context));
            }
        });

        // Create the Settings-button
        settingsBtn = new TextButton("Settings", skin);
        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                context.getScreens().push(new Settings(context));
            }
        });
        background = new Sprite(new Texture(Gdx.files.internal("virusWar.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // add buttons to table
        table.padTop(Constants.SCREEN_HEIGHT_SCALE * 20);
        table.add(playBtn).padBottom(Constants.SCREEN_HEIGHT_SCALE * 20);
        table.row();
        table.add(tutorialBtn).padBottom(Constants.SCREEN_HEIGHT_SCALE * 20);
        table.row();
        table.add(settingsBtn);
        tutorialBtn.setColor(Color.RED);
        settingsBtn.setColor(Color.RED);
        playBtn.setColor(Color.RED);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        SpriteBatch sb= context.getBatch();
        stage.act(dt);
        sb.begin();
        background.draw(sb);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {

    }


}
