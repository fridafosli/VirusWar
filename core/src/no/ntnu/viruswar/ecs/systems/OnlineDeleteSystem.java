package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.DeletedComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.LootComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class OnlineDeleteSystem extends IteratingSystem {

    private final LobbyController lobby;
    private final HashMap<String, Entity> entityMap;
    private final ComponentMapper<IdentifierComponent> idMapper;
    private final Context context;

    public OnlineDeleteSystem(Context context, LobbyController lobby) {
        super(Family.all(IdentifierComponent.class, LootComponent.class).get());
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        this.lobby = lobby;
        this.context = context;
        this.entityMap = new HashMap();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Map.Entry<String, Entity> entity : entityMap.entrySet()) {
            if (!lobby.getLoots().containsKey(entity.getKey())) {
                this.context.getBackend().removeLootFromGame(lobby.getPin(), entity.getKey());
            }
        }

        entityMap.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityMap.put(idMapper.get(entity).id, entity);
    }
}
