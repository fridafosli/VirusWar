package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.componenets.HiddenComponent;
import no.ntnu.viruswar.componenets.ConsumableComponent;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class ConsumingSystem extends IteratingSystem {

    private final ComponentMapper<ConsumableComponent> consumableMapper;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;


    private final Array<Entity> entityQueue;

    public ConsumingSystem() {
        super(Family.all(ConsumableComponent.class, TransformComponent.class, RectangleComponent.class).exclude(HiddenComponent.class).get());
        this.consumableMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        this.entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Entity clientPlayer = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).get(0);
        Vector3 clientPos = transformMapper.get(clientPlayer).position;
        float clientSize = consumableMapper.get(clientPlayer).size;

        for (Entity entity : entityQueue) {
            if (entity.equals(clientPlayer)) {
                continue;
            }
            float distance = clientPos.cpy().sub(transformMapper.get(entity).position).len();
            if (distance < 10) {
                Entity smallest = (clientSize < consumableMapper.get(entity).size) ? clientPlayer : entity;
                Entity largest = (smallest == entity) ? clientPlayer : entity;
                consumableMapper.get(largest).size += consumableMapper.get(smallest).size;
                rectangleMapper.get(largest).rect.setSize(rectangleMapper.get(largest).rect.width + consumableMapper.get(smallest).size);
                System.out.println(smallest.getComponent(ConsumableComponent.class).size);
                System.out.println(largest.getComponent(ConsumableComponent.class).size);
                smallest.remove(ConsumableComponent.class);
                smallest.add(new HiddenComponent());
            }
        }
        entityQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
