package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;

public class UpdateSizeSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final ComponentMapper<ConsumableComponent> consumMapper;
    private final ComponentMapper<DimensionComponent> dimMapper;

    public UpdateSizeSystem() {
        super(Family.all(ConsumableComponent.class, DimensionComponent.class).get());
        this.consumMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.dimMapper = ComponentMapper.getFor(DimensionComponent.class);
        this.entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (Entity entity : entityQueue) {
            float size = consumMapper.get(entity).size;
            dimMapper.get(entity).setSize(size);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
