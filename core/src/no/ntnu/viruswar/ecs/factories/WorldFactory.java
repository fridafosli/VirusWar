package no.ntnu.viruswar.ecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.MapComponent;
import no.ntnu.viruswar.ecs.componenets.TextureRegionComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.utils.Constants;

public class WorldFactory {

    public static Entity createWorld(PooledEngine engine) {
        Entity entity = engine.createEntity();
        DimensionComponent rc = new DimensionComponent(500, 500); //update to actual size
        TextureRegionComponent txc = new TextureRegionComponent(AssetManager.getInstance().getTextureRegion("hueCircle"));
        MapComponent mc = new MapComponent();
        TransformComponent tc = new TransformComponent(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT / 2);
        txc.zIndex = 0;
        entity.add(mc);
        entity.add(rc);
        entity.add(txc);
        entity.add(tc);
        return entity;
    }
}
