package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.componenets.LootComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;

public class LootSpawnSystem extends IteratingSystem {

    // May want to rename class: if the loot should have a constant placement
    // "on the map" to the background

    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> renderQueue;


    public LootSpawnSystem() {
        super(Family.all(LootComponent.class, TransformComponent.class, VelocityComponent.class).get());
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class); //skip if constant placement
        renderQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : renderQueue){
            RectangleComponent rtc = rectangleMapper.get(entity);
            // NOT DONE
            // need to do some thinking :)
        }

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
