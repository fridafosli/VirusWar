package no.ntnu.viruswar.ecs.factories;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.LootComponent;
import no.ntnu.viruswar.ecs.componenets.OnlineComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TextureComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.model.Loot;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.utils.Constants;

public class ActorFactory {

    private static final Random random = new Random();

    public static Vector2 randomPos(float maxDistanceFromCenter) {

        float rand = random.nextFloat();

        return new Vector2()
                .setToRandomDirection()
                .setLength(rand * maxDistanceFromCenter)
                .add(Constants.GAME_WORLD_CENTER);
    }

    private static Entity BaseActor(Engine engine, String id, float x, float y, float size, String texture) {
        Entity entity = engine.createEntity();
        entity.add(new IdentifierComponent(id));
        TextureComponent txc = new TextureComponent();
        txc.region = AssetManager.getInstance().getTexture(texture);
        entity.add(txc);
        entity.add(new TransformComponent(x, y));
        DimensionComponent dic = new DimensionComponent(0, 0);
        entity.add(new ConsumableComponent(size));
        dic.setSize(size);
        entity.add(dic);

        return entity;
    }

    public static Entity UserVirus(Engine engine, Player player) {
        Vector2 pos = randomPos(100);
        Entity entity = BaseActor(engine, player.getId(), pos.x, pos.y, 50, "virus");
        entity.add(new PlayerComponent());
        entity.add(new VelocityComponent());
        return entity;
    }

    public static Entity OnlineVirus(Engine engine, Player player) {
        Entity entity = BaseActor(engine, player.getId(), player.x, player.y, player.getPoints(), "virus");
        entity.add(new OnlineComponent());
        entity.add(new VelocityComponent());
        return entity;
    }

    public static Entity Loot(Engine engine, Loot loot) {
        Entity entity = BaseActor(engine, loot.getId(), loot.x, loot.y, loot.getPoints(), "loot");
        entity.add(new LootComponent());
        return entity;
    }
}
