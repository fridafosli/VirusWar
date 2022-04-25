package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.utils.Constants;
/*Defines the basic logic in menu screens, such as skin, stage and table to avoid repeatign code*/
abstract class MenuBaseScreen extends ScreenContext {
    protected Stage stage;
    protected Skin skin;
    protected Table table;

    protected MenuBaseScreen(Context context) {
        super(context);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Constants.FONT_SCALE);
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        stage.addActor(table);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.input.setOnscreenKeyboardVisible(false);
        stage.unfocusAll();
    }
}
