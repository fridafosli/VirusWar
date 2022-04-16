package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.componenets.ConsumableComponent;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.HiddenComponent;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class ZoomSystem extends IteratingSystem {
    private final ComponentMapper<ConsumableComponent> consumableMapper;


    private final Array<Entity> entityQueue;
    private Camera camera;

    private float prevSize = 0;

    public ZoomSystem(Camera camera) {
        super(Family.all(ConsumableComponent.class, TransformComponent.class, DimensionComponent.class).exclude(HiddenComponent.class).get());
        this.consumableMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.entityQueue = new Array<>();
        this.camera = camera;

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Entity clientPlayer = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).get(0);
        if (prevSize == 0) {
            prevSize =  consumableMapper.get(clientPlayer).size;
        }
        if(prevSize < consumableMapper.get(clientPlayer).size) {
            prevSize =  consumableMapper.get(clientPlayer).size;
            camera.zoom();
        }
        entityQueue.clear();

    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
