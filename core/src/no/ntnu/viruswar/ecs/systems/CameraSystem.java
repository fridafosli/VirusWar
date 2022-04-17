package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.utils.Camera;

public class CameraSystem extends IteratingSystem {
    private final ComponentMapper<TransformComponent> transformMapper;


    private final Array<Entity> entityQueue;
    private final Camera camera;

    public CameraSystem(Camera camera) {
        super(Family.all(TransformComponent.class).exclude(HiddenComponent.class).get());
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.entityQueue = new Array<>();
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Entity clientPlayer = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).get(0);
        Vector3 clientPos = transformMapper.get(clientPlayer).position;
        for (Entity entity : entityQueue) {
            if (entity.equals(clientPlayer)) {
                continue;
            }
            camera.setPosition(clientPos);
        }
        entityQueue.clear();

    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
