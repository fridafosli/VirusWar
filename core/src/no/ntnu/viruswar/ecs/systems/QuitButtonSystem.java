package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ButtonComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.TextureComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.screens.MainMenu;

public class QuitButtonSystem extends IntervalSystem {

    private TextButton quitBtn;
    private Context context;
    private final SpriteBatch batch;
    private final Skin skin;

    public QuitButtonSystem(SpriteBatch batch, final Context context, int priority) {
        super(Family.all(ButtonComponent.class).get(), priority);
        this.context = context;
        this.batch = batch;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Gdx.graphics.getHeight() / 300);
        // Set up Back-button
        quitBtn = new TextButton("Quit", skin);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        quitBtn.setPosition(0, 0);
        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("quit", "clicked");
                while (!(context.getScreens().peek() instanceof MainMenu)) {
                    context.getScreens().pop();
                }
            }
        });
        batch.begin();
        quitBtn.draw(batch, 1);
        batch.end();

    }

    @Override
    protected void updateInterval() {

    }
}
