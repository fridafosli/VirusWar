package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DeletedComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.OnlinePathComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class OnlineConsumingSystem extends IteratingSystem {

    private final ComponentMapper<ConsumableComponent> consumableMapper;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<DimensionComponent> rectangleMapper;
    private final ComponentMapper<IdentifierComponent> idMapper;

    private final Array<Entity> entityQueue;
    private final Context context;
    private final LobbyController lobby;
    private final ComponentMapper<OnlinePathComponent> pathMapper;

    public OnlineConsumingSystem(Context context, LobbyController lobby) {
        super(Family.all(ConsumableComponent.class, TransformComponent.class, DimensionComponent.class, IdentifierComponent.class, OnlinePathComponent.class).exclude(HiddenComponent.class).get());
        this.consumableMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.rectangleMapper = ComponentMapper.getFor(DimensionComponent.class);
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        this.pathMapper = ComponentMapper.getFor(OnlinePathComponent.class);
        this.entityQueue = new Array<>();
        this.context = context;
        this.lobby = lobby;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        Entity clientPlayer = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).get(0);
        if (consumableMapper.get(clientPlayer).isConsumed) return;

        Vector3 clientPos = transformMapper.get(clientPlayer).position;
        float clientSize = consumableMapper.get(clientPlayer).size;

        for (Entity entity : entityQueue) {
            if (entity.equals(clientPlayer) || consumableMapper.get(entity).isConsumed || consumableMapper.get(clientPlayer).isConsumed) {
                continue;
            }

            // If the distance is smaller than the combined radii the entities overlap
            float distance = clientPos.cpy().sub(transformMapper.get(entity).position).len();

            // Find the smallest and largest entity; the smallest will be absorbed into the largest
            if (distance < rectangleMapper.get(entity).getRadius() + rectangleMapper.get(clientPlayer).getRadius()) {
                Entity smallest = (clientSize < consumableMapper.get(entity).size) ? clientPlayer : entity;
                Entity largest = (smallest == entity) ? clientPlayer : entity;
                consumableMapper.get(largest).size += consumableMapper.get(smallest).size;

                // Hide the smallest entity
                smallest.add(new DeletedComponent());
                smallest.add(new HiddenComponent());
                consumableMapper.get(smallest).isConsumed = true;
                context.getBackend().setEntityConsumedState(lobby.getPin(), pathMapper.get(entity).pathName, idMapper.get(smallest).id, true);
            }

        }

        entityQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
