package no.ntnu.viruswar.screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.TouchController;
import no.ntnu.viruswar.factories.VirusFactory;
import no.ntnu.viruswar.managers.AssetManager;
import no.ntnu.viruswar.systems.ConsumingSystem;
import no.ntnu.viruswar.systems.PlayerControlSystem;
import no.ntnu.viruswar.systems.PlayerMovementSystem;
import no.ntnu.viruswar.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private PooledEngine engine;
    private final SpriteBatch batch;

    private final Camera camera;
    private final TouchController touchController;

    public GameScreen(SpriteBatch batch) {
        super();
        this.batch = batch;
        camera = new Camera();
        touchController = new TouchController(camera);
        Gdx.input.setInputProcessor(touchController);
    }

    private void init() {
        engine = new PooledEngine();

        engine.addSystem(new PlayerControlSystem(touchController));
        engine.addSystem(new PlayerMovementSystem());
        engine.addSystem(new RenderingSystem(batch, camera));
        engine.addSystem(new ConsumingSystem());

        engine.addEntity(VirusFactory.createVirus(engine, 100, 100, false));
        engine.addEntity(VirusFactory.createVirus(engine, 150, 150, true));
    }

    private void update(float dt) {
        engine.update(dt);
    }

    @Override
    public void render(float dt) {
        if (engine == null) {
            init();
        }
        update(dt);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.getInstance().dispose();
    }
}

