package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

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



        for (Entity entity : entityQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);
            Vector3 centerToPlayer = new Vector3(trc.position.x - radius, trc.position.y -radius, 0);
            //tror egt dette er ish riktig tanke men blir fucka pga banen og koordinatene
            if (vcc.velocity.len() > 0.1 && centerToPlayer.add(vcc.velocity).len() < radius) {
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


