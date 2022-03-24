package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.LootSpawnController;
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
    private LootSpawnController lsc;


    public LootSpawnSystem() {
        super(Family.all(LootComponent.class, TransformComponent.class, VelocityComponent.class).get());
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class); //skip if constant placement
        renderQueue = new Array<Entity>();
        //Not sure about velocity.

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        lsc.decrementWait();
        for (Entity entity : renderQueue){
            RectangleComponent rtc = rectangleMapper.get(entity);
            if (lsc.generateSpawnPos() == null){
                continue;
                //Wait between spawns not done
            }
            // else: create a new loot with the position
            // NOT DONE
            // need to do some thinking :)
        }

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
