package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.factories.ActorFactory;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.services.models.Loot;

public class LootSpawnSystem extends IntervalSystem {

    private final ComponentMapper<DimensionComponent> dimensionMapper;
    private final Entity worldMap;
    private final Context context;
    private final LobbyController lobby;
    private final Random random;


    public LootSpawnSystem(float interval, Entity worldMap, Context context, LobbyController lobby) {
        super(interval);
        this.worldMap = worldMap;
        this.context = context;
        this.lobby = lobby;
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);
        this.random = new Random();
    }

    @Override
    protected void updateInterval() {
        Vector2 pos = ActorFactory.randomPos(dimensionMapper.get(worldMap).getRadius());
        Loot loot = new Loot(pos.x, pos.y, 200 * random.nextFloat());
        context.getBackend().addEntityToGame(lobby.getPin(), loot);
    }

}
