package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;


public class PlayerMovementSystem extends IteratingSystem {

    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<DimensionComponent> dimensionMapper;

    private Entity mapEntity;

    public PlayerMovementSystem(Entity mapEntity) {//Entity worldMap
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);

        this.mapEntity = mapEntity;
        entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        DimensionComponent mapDimension = dimensionMapper.get(mapEntity);

        float radius = mapDimension.getRadius();

        Vector3 center = new Vector3(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT /2, 0);

        for (Entity entity : entityQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);
            if (vcc.velocity.len() > 0.1) {
                trc.position.add(vcc.velocity.scl(deltaTime));
            }

            Vector3 centerToPlayer = trc.position.cpy().sub(center);
            float length = centerToPlayer.len() + dimensionMapper.get(entity).getRadius();
            if (length > radius) {
                trc.position.sub(
                        // The vector from the circle to the player in line with center of the map
                       centerToPlayer.cpy().sub(centerToPlayer.cpy().scl(radius / length))
                );
            }
        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}


