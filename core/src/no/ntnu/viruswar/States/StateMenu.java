package no.ntnu.viruswar.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

abstract class StateMenu extends State{

    protected Stage stage;
    protected Skin skin;
    protected Table table;
    protected Sprite background;

    protected StateMenu(GameStateManager gsm) {
        super(gsm);
        // Create skin
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        //skin.getFont("default-font").getData().setScale(scale);

        // Create stage
        stage = new Stage(new ScreenViewport());

        // Create table
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        stage.addActor(table);

        // set the InputProcessor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected InputProcessor getInputprosesspr() {
        return stage;
    }
}
