package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ButtonComponent;

public class QuitButtonSystem extends IteratingSystem {

    private final TextButton backBtn;
    private Context context;
    private final SpriteBatch batch;
    private final Skin skin;

    public QuitButtonSystem(SpriteBatch batch, final Context context, Skin skin, int priority) {
        super(Family.all(ButtonComponent.class).get(), priority);
        this.context = context;
        this.batch = batch;
        this.skin = skin;

        // Set up Back-button
        backBtn = new TextButton("Back", skin);
        backBtn.setPosition(0, Gdx.graphics.getHeight() - 70);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("back", "clicked");
                context.getScreens().pop();
            }
        });
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
