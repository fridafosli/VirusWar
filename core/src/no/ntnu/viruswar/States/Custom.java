package no.ntnu.viruswar.States;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Custom extends State{
    protected Stage stage;
    protected Skin skin;
    protected Sprite background;

    private TextButton backBtn;
    public Custom(final GameStateManager gsm){
        super(gsm);
        stage = new Stage(new ScreenViewport());
    }

    @Override
    protected InputProcessor getInputprosesspr() {
        return null;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
