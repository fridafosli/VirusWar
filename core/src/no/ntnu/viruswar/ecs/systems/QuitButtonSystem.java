package no.ntnu.viruswar.ecs.systems;


import static com.badlogic.gdx.utils.Align.bottomLeft;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ButtonComponent;
import no.ntnu.viruswar.ecs.utils.Camera;
import no.ntnu.viruswar.ecs.utils.TouchController;
import no.ntnu.viruswar.screens.MainMenu;

public class QuitButtonSystem extends IteratingSystem {

    private TextButton quitBtn;
    private Context context;
    private final SpriteBatch batch;
    private final Camera camera;
    private final Skin skin;
    private Entity button;
    private ButtonComponent buttComp = new ButtonComponent();
    private final Engine engine;
    private final TouchController touchController;

    public QuitButtonSystem(TouchController tcon, Engine engine, final Context context, Camera camera, int priority) {
        super(Family.all(ButtonComponent.class).get(), priority);
        this.context = context;
        this.touchController = tcon;
        this.batch = context.getBatch();
        this.engine = engine;
        this.camera = camera;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin.getFont("default-font").getData().setScale(Gdx.graphics.getHeight() / 300);
        // Set up Back-button
        quitBtn = new TextButton("Quit", skin);
        Entity ent = engine.createEntity();
        ent.add(buttComp);
        engine.addEntity(ent);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.setProjectionMatrix(camera.combined);
        quitBtn.setTransform(true);
        quitBtn.setScale(0.2f);
        quitBtn.setPosition(camera.position.x - 5 - quitBtn.getWidth()*0.2f + camera.viewportWidth/2, camera.position.y - 5 - quitBtn.getHeight()*0.2f+camera.viewportHeight/2);
        if (touchController.touchQuitButton(quitBtn.getX(bottomLeft), quitBtn.getY(bottomLeft))) {
            Gdx.app.log("quit", "clicked");
            while (!(context.getScreens().peek() instanceof MainMenu)) {
                context.getScreens().pop();
            }
        }
        batch.begin();
        quitBtn.draw(batch, 1);
        batch.end();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.button = entity;
    }
}

