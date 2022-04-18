package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import no.ntnu.viruswar.context.Context;

public class Settings extends ContextScreen {

    protected Stage stage;
    protected Skin skin;
    protected Sprite background;
    private final TextButton backBtn;

    public Settings(final Context context) {
        super(context);
        // Create the skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.getFont("default-font").getData().setScale(scale);

        stage = new Stage(new ScreenViewport());

        // Setting up the back button
        backBtn = new TextButton("Back", skin);
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
        background = new Sprite(new Texture(Gdx.files.internal("basicBackground.png")));
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
