package no.ntnu.viruswar.ecs.factories;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.OnlineComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TextureComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.model.Player;

public class VirusFactory {

    static float initial_size = 50;

    static public Entity createVirus(Engine engine, float x, float y, String id) {
        Entity entity = engine.createEntity();

        entity.add(new IdentifierComponent(id));

        TransformComponent tfc = new TransformComponent((float) (Math.random() * 150) + 20 , (float) (Math.random() * 150) + 20);
        entity.add(tfc);

        TextureComponent txc = new TextureComponent();
        txc.region = AssetManager.getInstance().getTexture("virus");
        entity.add(txc);

        DimensionComponent rtc = new DimensionComponent(0, 0);
        rtc.setSize(initial_size);

        entity.add(rtc);

        entity.add(new VelocityComponent());

        entity.add(new ConsumableComponent(initial_size));

        return entity;
    }


    static public Entity createPlayerVirus(Engine engine, float x, float y, String id) {
        Entity entity = createVirus(engine, x, y, id);
        entity.add(new PlayerComponent());
        return entity;
    }

    static public Entity createOnlineVirus(Engine engine, Player player) {
        Entity entity = createVirus(engine, player.x, player.y, player.getId());
        entity.add(new OnlineComponent());
        return entity;
    }





}
