package no.ntnu.viruswar.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import no.ntnu.viruswar.componenets.ConsumableComponent;
import no.ntnu.viruswar.componenets.OnlinePlayerComponent;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;
import no.ntnu.viruswar.managers.AssetManager;

public class VirusFactory {

    static float initial_size = 50;

    static public Entity createVirus(PooledEngine engine, float x, float y, boolean online) {
        Entity entity = engine.createEntity();

        TransformComponent tfc = new TransformComponent(x, y);
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
