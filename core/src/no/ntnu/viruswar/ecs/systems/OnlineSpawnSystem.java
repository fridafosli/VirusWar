package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.factories.ActorFactory;
import no.ntnu.viruswar.ecs.factories.LootFactory;
import no.ntnu.viruswar.ecs.factories.VirusFactory;
import no.ntnu.viruswar.services.backend.model.Loot;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class OnlineSpawnSystem extends IteratingSystem {

    private final HashMap<String, Entity> entityMap;
    private final LobbyController controller;
    final Context context;
    private final ComponentMapper<IdentifierComponent> idMapper;


    public OnlineSpawnSystem(Context context, LobbyController controller) {
        super(Family.all(IdentifierComponent.class).get());
        this.entityMap = new HashMap<>();
        this.controller = controller;
        this.context = context;
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);


    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Create players from firebase that are not yet added.
        for (Map.Entry<String, Player> player : controller.getPlayers().entrySet()) {
            if (!player.getKey().equals(controller.getState().getPlayerId()) && !entityMap.containsKey(player.getKey())) {
                this.getEngine().addEntity(ActorFactory.OnlineVirus(this.getEngine(), player.getValue()));
            }
        }

        // Create loot from firebase that are not yet added.
        for (Map.Entry<String, Loot> loot : controller.getLoots().entrySet()) {
            if (!entityMap.containsKey(loot.getKey())) {
                this.getEngine().addEntity(ActorFactory.Loot(this.getEngine(), loot.getValue()));
            }
        }

        entityMap.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityMap.put(idMapper.get(entity).id, entity);
    }
}
