package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.OnlineComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.factories.VirusFactory;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class OnlineSpawnSystem extends IteratingSystem {

    private final HashMap<String, Entity> entityMap;
    private final LobbyController controller;
    private final ComponentMapper<IdentifierComponent> idMapper;


    public OnlineSpawnSystem(LobbyController controller) {
        super(Family.all(IdentifierComponent.class, OnlineComponent.class).get());
        this.entityMap = new HashMap<>();
        this.controller = controller;
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Map.Entry<String, Player> player : controller.getPlayers().entrySet()) {
            if (!player.getKey().equals(controller.getState().getPlayerId()) && !entityMap.containsKey(player.getKey())) {
                this.getEngine().addEntity(VirusFactory.createOnlineVirus((PooledEngine)this.getEngine(), player.getValue()));
            }
        }

        for (Map.Entry<String, Entity>  entity : entityMap.entrySet()) {
            if (!controller.getPlayers().containsKey(entity.getKey())) {
                this.getEngine().removeEntity(entity.getValue());
            }
        }

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityMap.put(idMapper.get(entity).id, entity);
    }
}
