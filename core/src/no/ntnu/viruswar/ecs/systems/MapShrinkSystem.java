package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
//sortetd iterating system in renderingsystem

import no.ntnu.viruswar.utils.Constants;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.LootComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;

public class MapShrinkSystem extends IntervalIteratingSystem {

    private final ComponentMapper<DimensionComponent> dimensionMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    private Entity mapEntity;
    private final Array<Entity> entityQueue;


    public MapShrinkSystem(float interval, Entity mapEntity) {
        super(Family.all(LootComponent.class).get(), interval);
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.mapEntity = mapEntity;
        entityQueue = new Array<>();

    }

    private void shrink(){

        DimensionComponent dc = dimensionMapper.get(mapEntity);
        dc.add(-10);
    }

    @Override
    protected void updateInterval() {
        super.updateInterval();

        shrink();

        System.out.println(entityQueue.size);
        for (Entity entity : entityQueue){
            DimensionComponent mapDimension = dimensionMapper.get(mapEntity);
            Vector3 center = new Vector3(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT /2, 0);

            TransformComponent trc = transformMapper.get(entity);
            Vector3 centerToLoot = trc.position.cpy().sub(center);
            float length = centerToLoot.len();
            if (length > mapDimension.getRadius()){
                entity.add(new HiddenComponent());
            }
        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity) {
        entityQueue.add(entity);

    }

}
