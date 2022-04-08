package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Custom extends State{
    protected Stage stage;
    protected Skin skin;
    protected Sprite background;
    protected Sprite playerVirus;
    private Color color;
    private TextButton backBtn;
    public Custom(final GameStateManager gsm){
        super(gsm);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.getFont("default-font").getData().setScale(scale);

        stage = new Stage(new ScreenViewport());

        // Setting up the back button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Settings", "clicked");
                gsm.pop();
            }
        });
        stage.addActor(backBtn);
    setPlayerVirus();
        // Set the background
        background = new Sprite(new Texture(Gdx.files.internal("basicBackground.png")));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set the inputProcessor
        Gdx.input.setInputProcessor(stage);
    }

    private void setPlayerVirus(){
        playerVirus= new Sprite(new Texture("virus.png"));
        List<Color> colors= Arrays.asList(Color.BLUE,Color.PINK,Color.CYAN, Color.RED, Color.GREEN, Color.MAGENTA, Color.BROWN,
                Color.FIREBRICK, Color.FOREST, Color.PURPLE, Color.CORAL, Color.LIME, Color.SKY,Color.ORANGE, Color.OLIVE,Color.YELLOW, Color.VIOLET, Color.WHITE,Color.GOLDENROD, Color.SALMON, Color.MAROON, Color.NAVY);

        color= colors.get((int)(Math.random()*(colors.size()-1)));
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
        sb.draw(playerVirus, Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2-100, 250,250);
        sb.setColor(color);
        sb.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();



    }

    @Override
    public void dispose() {

    }
}
