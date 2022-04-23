package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DeletedComponent;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class OnlineDeleteSystem extends IteratingSystem {

    private final LobbyController lobby;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<IdentifierComponent> idMapper;

    public OnlineDeleteSystem(LobbyController lobby) {
        super(Family.all(IdentifierComponent.class, ConsumableComponent.class).exclude(DeletedComponent.class).get());
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        this.lobby = lobby;
        this.entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // If the entity is marked as consumed on the backend, mark it as deleted in the ecs
        for (Entity entity : entityQueue) {
            if (lobby.entityIsConsumed(idMapper.get(entity).id)) {
                entity.add(new HiddenComponent());
                entity.add(new DeletedComponent());
            }
        }

        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
