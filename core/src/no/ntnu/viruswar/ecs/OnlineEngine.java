package no.ntnu.viruswar.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.InputProcessor;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.factories.ActorFactory;
import no.ntnu.viruswar.ecs.factories.WorldFactory;
import no.ntnu.viruswar.ecs.systems.CameraSystem;
import no.ntnu.viruswar.ecs.systems.GameStateSystem;
import no.ntnu.viruswar.ecs.systems.LootSpawnSystem;
import no.ntnu.viruswar.ecs.systems.MapShrinkSystem;
import no.ntnu.viruswar.ecs.systems.OnlineConsumingSystem;
import no.ntnu.viruswar.ecs.systems.OnlineControlSystem;
import no.ntnu.viruswar.ecs.systems.OnlineDeleteSystem;
import no.ntnu.viruswar.ecs.systems.OnlineSendUserSystem;
import no.ntnu.viruswar.ecs.systems.OnlineSpawnSystem;
import no.ntnu.viruswar.ecs.systems.PlayerControlSystem;
import no.ntnu.viruswar.ecs.systems.PlayerMovementSystem;
import no.ntnu.viruswar.ecs.systems.QuitButtonSystem;
import no.ntnu.viruswar.ecs.systems.RenderingSystem;
import no.ntnu.viruswar.ecs.systems.ScoreSystem;
import no.ntnu.viruswar.ecs.systems.TextRenderSystem;
import no.ntnu.viruswar.ecs.systems.UpdateSizeSystem;
import no.ntnu.viruswar.ecs.utils.Camera;
import no.ntnu.viruswar.ecs.utils.EntityComparator;
import no.ntnu.viruswar.ecs.utils.TouchController;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.ecs.systems.ZoomSystem;

public class OnlineEngine {

    private final TouchController touchController;
    private final Camera camera;
    private final Context context;
    private final LobbyController lobby;
    private PooledEngine engine;

    public OnlineEngine(Context context, LobbyController lobby) {
        this.context = context;
        this.lobby = lobby;
        camera = new Camera();
        touchController = new TouchController(camera);
    }

    public void init() {
        engine = new PooledEngine();
        Entity mapEntity = WorldFactory.createWorld(engine);
        engine.addSystem(new MapShrinkSystem(5, mapEntity));
        engine.addEntity(mapEntity);
        engine.addSystem(new CameraSystem(camera));
        engine.addSystem(new PlayerControlSystem(touchController));
        engine.addSystem(new PlayerMovementSystem(mapEntity));
        engine.addSystem(new OnlineConsumingSystem(context, lobby));
        engine.addSystem(new RenderingSystem(context.getBatch(), camera, new EntityComparator()));
        engine.addSystem(new LootSpawnSystem(5, mapEntity, context, lobby)); //change to bigger spawn interval
        engine.addSystem(new OnlineSendUserSystem(context, lobby));
        engine.addSystem(new OnlineSpawnSystem(context, lobby));
        engine.addSystem(new OnlineDeleteSystem(lobby));
        engine.addSystem(new OnlineControlSystem(context, lobby));
        engine.addSystem(new UpdateSizeSystem());
        engine.addSystem(new GameStateSystem(context, lobby));
        engine.addSystem(new ScoreSystem(engine, lobby));
        engine.addSystem(new TextRenderSystem(1, context.getBatch(), camera));
        engine.addSystem(new QuitButtonSystem(touchController, engine, context, camera, 1));
        engine.addSystem(new ZoomSystem(camera));
        engine.addEntity(ActorFactory.UserVirus(engine, lobby.getPlayers().get(lobby.getState().getPlayerId())));
    }

    public void update(float dt) {
        if (engine == null) {
            init();
        }
        engine.update(dt);
    }

    public InputProcessor getInputProcessor() {
        return touchController;
    }

}
