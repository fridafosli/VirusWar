package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.assets.AssetManager;


public class MainMenu extends MenuBaseScreen {

    private final TextButton playBtn;
    private final TextButton tutorialBtn;
    private final TextButton settingsBtn;


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

        // add buttons to table
        table.padTop(scale * 100);
        table.add(playBtn).padBottom(scale * 50);
        table.row();
        table.add(tutorialBtn).padBottom(scale * 50);
        table.row();
        table.add(settingsBtn);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }

}
