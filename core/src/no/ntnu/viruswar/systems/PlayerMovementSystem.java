package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;


public class PlayerMovementSystem extends IteratingSystem {

    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<TransformComponent> transformMapper;

    public PlayerMovementSystem() {
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : entityQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);

            if (vcc.velocity.len() > 0.1) {
                trc.position.add(vcc.velocity.scl(deltaTime));
            }
        }

        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}


