package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Settings extends State {

    protected Stage stage;
    protected Skin skin;
    protected Sprite background;

    private TextButton backBtn;


    public Settings(final GameStateManager gsm) {
        super(gsm);

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
                gsm.pop();
            }
        });
        stage.addActor(backBtn);

        // Set the background
        background = new Sprite(new Texture(Gdx.files.internal("basicBackground.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set the inputProcessor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected InputProcessor getInputprosesspr() {
        return stage;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    public void render(SpriteBatch sb) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        background.draw(sb);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void dispose() {

    }

}
