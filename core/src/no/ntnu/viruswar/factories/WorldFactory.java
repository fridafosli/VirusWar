package no.ntnu.viruswar.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.MapComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.managers.AssetManager;

public class WorldFactory {

    public static Entity createWorld(PooledEngine engine){
        Entity entity = engine.createEntity();
        DimensionComponent rc = new DimensionComponent(150, 150); //update to actual size
        TextureComponent txc = new TextureComponent();
        MapComponent mc = new MapComponent();
        TransformComponent tc = new TransformComponent(Constants.GAME_WORLD_WIDTH /2,Constants.GAME_WORLD_HEIGHT /2);
        txc.region = AssetManager.getInstance().getTexture("map");
        txc.zIndex = 0;
        entity.add(mc);
        entity.add(rc);
        entity.add(txc);
        entity.add(tc);
        return entity;
    }
}
