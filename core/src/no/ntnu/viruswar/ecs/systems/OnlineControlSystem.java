package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.OnlineComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.ecs.utils.TouchController;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;


public class OnlineControlSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> transformMapper;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<IdentifierComponent> idMapper;
    private final LobbyController controller;
    private final ComponentMapper<ConsumableComponent> sizeMapper;

    public OnlineControlSystem(LobbyController controller) {
        super(Family.all(TransformComponent.class, OnlineComponent.class, IdentifierComponent.class, ConsumableComponent.class).get());
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        sizeMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.controller = controller; 
        entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : entityQueue) {
            TransformComponent trc = transformMapper.get(entity);
            IdentifierComponent idc = idMapper.get(entity);
            ConsumableComponent coc = sizeMapper.get(entity);
            
            Player player = controller.getPlayers().get(idc.id);

            trc.position.x = player.x;
            trc.position.y = player.y;
            coc.size = player.getPoints();

        }

        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}

