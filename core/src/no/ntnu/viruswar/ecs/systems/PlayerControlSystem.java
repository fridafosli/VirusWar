package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.ecs.utils.TouchController;


public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> entityQueue;
    private final TouchController touchController;

    public PlayerControlSystem(TouchController touchController) {
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        entityQueue = new Array<>();
        this.touchController = touchController;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : entityQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);

            if (!touchController.isTouching) {
                vcc.velocity.set(0, 0, 0);
                return;
            }
            Vector3 touch = touchController.getTouchInWorld();
            Vector3 position = transformMapper.get(entity).position;
            vcc.velocity.set(touch.cpy().sub(position).nor().scl(touch.cpy().sub(position).len()).scl(0.5f).clamp(0, 40));
        }

        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}

