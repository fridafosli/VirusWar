package no.ntnu.viruswar.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.factories.VirusFactory;
import no.ntnu.viruswar.ecs.factories.WorldFactory;
import no.ntnu.viruswar.ecs.systems.CameraSystem;
import no.ntnu.viruswar.ecs.systems.ConsumingSystem;
import no.ntnu.viruswar.ecs.systems.DecideLooserSystem;
import no.ntnu.viruswar.ecs.systems.DecideWinnerSystem;
import no.ntnu.viruswar.ecs.systems.LootSpawnSystem;
import no.ntnu.viruswar.ecs.systems.MapShrinkSystem;
import no.ntnu.viruswar.ecs.systems.PlayerControlSystem;
import no.ntnu.viruswar.ecs.systems.PlayerMovementSystem;
import no.ntnu.viruswar.ecs.systems.RenderingSystem;
import no.ntnu.viruswar.ecs.utils.Camera;
import no.ntnu.viruswar.ecs.utils.EntityComparator;
import no.ntnu.viruswar.ecs.utils.TouchController;
import no.ntnu.viruswar.services.screen.Screen;

public class GameScreen extends ContextScreen {

    private final Camera camera;
    private final TouchController touchController;
    private PooledEngine engine;

    public GameScreen(Context context) {
        super(context);
        camera = new Camera();
        touchController = new TouchController(camera);
    }

    private void init() {
        engine = new PooledEngine();
        Entity mapEntity = WorldFactory.createWorld(engine);
        engine.addSystem(new MapShrinkSystem(5, mapEntity));
        engine.addEntity(mapEntity);
        engine.addSystem(new CameraSystem(camera));
        engine.addSystem(new PlayerControlSystem(touchController));
        engine.addSystem(new PlayerMovementSystem(mapEntity));
        engine.addSystem(new ConsumingSystem(context));
        engine.addSystem(new RenderingSystem(context.getBatch(), camera, new EntityComparator()));
        engine.addSystem(new LootSpawnSystem(1, mapEntity)); //change to bigger spawn interval
        engine.addSystem(new DecideWinnerSystem(context));
        engine.addSystem(new DecideLooserSystem(context));
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
    public void show() {
        Gdx.input.setInputProcessor(touchController);
    }
}

