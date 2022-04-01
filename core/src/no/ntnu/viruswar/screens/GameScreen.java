package no.ntnu.viruswar.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.EntityComparator;
import no.ntnu.viruswar.TouchController;
import no.ntnu.viruswar.factories.VirusFactory;
import no.ntnu.viruswar.factories.WorldFactory;
import no.ntnu.viruswar.managers.AssetManager;
import no.ntnu.viruswar.systems.ConsumingSystem;
import no.ntnu.viruswar.systems.LootSpawnSystem;
import no.ntnu.viruswar.systems.MapShrinkSystem;
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
        Entity mapEntity = WorldFactory.createWorld(engine);
        engine.addSystem(new MapShrinkSystem(5, mapEntity));
        engine.addEntity(mapEntity);

        engine.addSystem(new PlayerControlSystem(touchController));
        engine.addSystem(new PlayerMovementSystem(mapEntity));
        engine.addSystem(new ConsumingSystem());
        EntityComparator comparator = new EntityComparator();
        engine.addSystem(new RenderingSystem(batch, camera, comparator));
        engine.addSystem(new LootSpawnSystem(1)); //change to bigger spawn interval
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

