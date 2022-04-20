package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.text.View;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.assets.AssetManager;

public class Settings extends ContextScreen {

    protected Stage stage;
    protected Skin skin;
    protected Sprite background;

    public Settings(final Context context) {
        super(context);
        // Create the skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Gdx.graphics.getHeight() / 300);

        //skin.getFont("default-font").getData().setScale(scale);
        stage = new Stage(new ScreenViewport());
        final Drawable volumeOn= new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("volume.png"))));
        final Drawable volumeOff = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("no-sound.png"))));
        final int i= 0;
        boolean buttonOn;
        final ImageButton sound = new ImageButton(skin);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPressed){
                    v.setBackgroundResource(R.drawable.normal);
                }else{
                    v.setBackgroundResource(R.drawable.pressed);
                }
                isPressed = !isPressed; // reverse
            }
        });


    }


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!buttonOn) {
                    buttonOn = true;
                    imageButton.setBackground(getResources().getDrawable(R.drawable.button_is_on));
                } else {
                    buttonOn = false;
                    imageButton.setBackground(getResources().getDrawable(R.drawable.button_is_off));
                }
            }
        });
        stage.addActor(sound);
        // Sets volume slider
        final Slider slider= new Slider( 0,  1,  0.1f, false, skin);
        slider.setVisualPercent(context.getAssets().getVolume());
        slider.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("slider", "interacted");
                context.getAssets().setMusic(true, slider.getPercent());

            }
        });
        Container<Slider> container=new Container<>(slider);
        container.setTransform(true);

        container.setPosition((Gdx.graphics.getWidth()-600)/2,300);
        container.setScale(Gdx.graphics.getHeight() / 250);

        stage.addActor(container);

       // stage.addActor(slider);
        // Setting up the back button
        TextButton backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 50);
        backBtn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                context.getScreens().pop();

            }
        });

        stage.addActor(backBtn);
        // Set the background
        background = new Sprite(new Texture(Gdx.files.internal("settings.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float dt) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        context.getBatch().begin();
        background.draw(context.getBatch());
        context.getBatch().end();

        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {

    }

}
