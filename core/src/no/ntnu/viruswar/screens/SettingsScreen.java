package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.utils.Constants;

public class SettingsScreen extends ScreenContext {
    private Stage stage;
    private Skin skin;
    private Label percentLabel;


    public SettingsScreen(final Context context) {
        super(context);

        stage = new Stage(new ScreenViewport());
        // Create the skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Constants.FONT_SCALE);

        // Setting up mute button
        final Drawable volOnTexture = new TextureRegionDrawable(new Texture(Gdx.files.internal("volOn.png")));
        Drawable volOffTexture = new TextureRegionDrawable(new Texture(Gdx.files.internal("volOff.png")));
        final ImageButton muteBtn = new ImageButton(volOnTexture, volOffTexture, volOffTexture);
        muteBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                context.getAssets().setMusic(!context.getAssets().getPlaying(), context.getAssets().getVolume());
            }
        });
        muteBtn.setSize(Constants.SCREEN_WIDTH_SCALE * 6, Constants.SCREEN_WIDTH_SCALE * 6);
        muteBtn.setPosition(Gdx.graphics.getWidth() / 2f - muteBtn.getWidth() / 2, Constants.SCREEN_HEIGHT_SCALE * 35);
        stage.addActor(muteBtn);


        Label volumeLabel = new Label("Set music volume", skin);
        volumeLabel.setPosition(Gdx.graphics.getWidth() / 2f - volumeLabel.getWidth() / 2,
                Constants.SCREEN_HEIGHT_SCALE * 70 - volumeLabel.getHeight() / 2);
        stage.addActor(volumeLabel);

        // Sets volume slider
        final Slider slider = new Slider(0, 1, 0.1f, false, skin);
        slider.setVisualPercent(context.getAssets().getVolume());
        slider.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("slider", "interacted");
                context.getAssets().setMusic(true, slider.getPercent());
                percentLabel.setText((int) (slider.getPercent() * 100) + "%");
            }
        });


        // put slider in container for scaling
        Container<Slider> container = new Container<>(slider);
        container.setTransform(true);
        container.setPosition((Gdx.graphics.getWidth() - container.getWidth()) / 2,
                Gdx.graphics.getHeight() / 2);
        container.setScale(Gdx.graphics.getHeight() / 250f);
        stage.addActor(container);

        // Setting up label showing volume-percent
        percentLabel = new Label((int) (slider.getPercent() * 100) + "%", skin);
        percentLabel.setPosition(Gdx.graphics.getWidth() / 2f - slider.getWidth() / 2f, 560);
        stage.addActor(percentLabel);

        // Setting up the back button
        TextButton backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - backBtn.getWidth());
        backBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                context.getScreens().pop();

            }
        });
        stage.addActor(backBtn);
        // Set the background
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        context.getBatch().begin();
        context.getBatch().end();

        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }

}
