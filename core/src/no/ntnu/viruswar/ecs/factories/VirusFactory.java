package no.ntnu.viruswar.ecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.OnlinePlayerComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.TextureComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.assets.AssetManager;

public class VirusFactory {

    static float initial_size = 50;

    static public Entity createVirus(PooledEngine engine, float x, float y, boolean online) {
        Entity entity = engine.createEntity();

        TransformComponent tfc = new TransformComponent(100, 100);
        entity.add(tfc);

        TextureComponent txc = new TextureComponent();
        txc.region = AssetManager.getInstance().getTexture("virus");
        entity.add(txc);

        DimensionComponent rtc = new DimensionComponent(0, 0);
        rtc.setSize(initial_size);

        entity.add(rtc);

        entity.add(new VelocityComponent());

        entity.add(new ConsumableComponent(initial_size));


        if (!online) {
            entity.add(new PlayerComponent());
        } else {
            entity.add(new OnlinePlayerComponent());
        }

        return entity;
    }

}
