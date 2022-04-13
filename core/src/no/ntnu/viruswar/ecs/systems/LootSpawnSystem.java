package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import no.ntnu.viruswar.utils.Constants;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.factories.LootFactory;

public class LootSpawnSystem extends IntervalSystem {

    // May want to rename class: if the loot should have a constant placement
    // "on the map" to the background

    /*private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper;
    private final ComponentMapper<LootComponent> lootComponentComponentMapper;
    private final ComponentMapper<TextureComponent> textureComponentComponentMapper;*/

    //private final LootSpawnController lootSpawnController;
    // Has no velocity, static with respect to background

    private Entity worldMap;
    private final ComponentMapper<DimensionComponent> dimensionMapper;


    public LootSpawnSystem(float interval, Entity worldMap) {
        super(interval);
        this.worldMap = worldMap;
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);


    }

    @Override
    protected void updateInterval() {
        DimensionComponent mapDimension = dimensionMapper.get(worldMap);
        Random random = new Random();
        Vector3 center = new Vector3(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT /2, 0);
        float length = 1000;
        Vector3 lootPoint = new Vector3(0,0,0);

        // make sure loot only spawns withing current world map size
        while (length > mapDimension.getRadius()){
            lootPoint.set(random.nextInt((int) mapDimension.width*2), random.nextInt((int) mapDimension.height*2), 0);
            Vector3 centerToLoot = lootPoint.cpy().sub(center);
            length = centerToLoot.len() ;
        }
        getEngine().addEntity(LootFactory.createEntity(getEngine(), lootPoint.x , lootPoint.y));
    }

}
