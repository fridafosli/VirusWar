package no.ntnu.viruswar.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import no.ntnu.viruswar.componenets.ConsumableComponent;
import no.ntnu.viruswar.componenets.LootComponent;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.managers.AssetManager;

public class LootFactory {

    static float initial_size = 50;

    public static Entity createEntity(Engine engine, float x, float y) {
        Entity entity = engine.createEntity();
        TextureComponent txc = new TextureComponent();
        txc.region = AssetManager.getInstance().getTexture("loot");
        entity.add(new TransformComponent(x, y));
        entity.add(new LootComponent());
        DimensionComponent dic = new DimensionComponent(0, 0);
        dic.setSize(initial_size);
        entity.add(dic);
        entity.add(new ConsumableComponent(initial_size));
        entity.add(txc);
        return entity;
    }
}
