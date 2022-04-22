package no.ntnu.viruswar.ecs.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.LootComponent;
import no.ntnu.viruswar.ecs.componenets.TextureComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.model.Loot;

public class LootFactory {

    static float initial_size = 50;

    public static Entity createEntity(Engine engine, float x, float y, String id) {
        Entity entity = engine.createEntity();
        TextureComponent txc = new TextureComponent();
        entity.add(new IdentifierComponent(id));
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

    public static Entity createEntity(Engine engine, Loot loot) {
        return createEntity(engine, loot.x, loot.y, loot.getId());
    }
}
