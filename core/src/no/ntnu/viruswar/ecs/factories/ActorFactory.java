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
import no.ntnu.viruswar.ecs.componenets.OnlinePathComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TextureRegionComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.models.Loot;
import no.ntnu.viruswar.services.models.Player;
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

    private static Entity BaseActor(Engine engine, String id, float x, float y, float size) {
        Entity entity = engine.createEntity();
        entity.add(new IdentifierComponent(id));
        entity.add(new TransformComponent(x, y));
        DimensionComponent dic = new DimensionComponent(0, 0);
        entity.add(new ConsumableComponent(size));
        dic.setSize(size);
        entity.add(dic);
        return entity;
    }

    private static void addVirusComponents(Entity entity, Player player) {
        entity.add(new VelocityComponent());
        entity.add(new OnlinePathComponent(player.getPath()));
        entity.add(new TextureRegionComponent(AssetManager.getInstance().getViruses(player.getSkinIndex())));
    }

    public static Entity UserVirus(Engine engine, Player player) {
        Vector2 pos = randomPos(100);
        Entity entity = BaseActor(engine, player.getId(), pos.x, pos.y, 50);
        entity.add(new PlayerComponent());
        addVirusComponents(entity, player);
        return entity;
    }

    public static Entity OnlineVirus(Engine engine, Player player) {
        Entity entity = BaseActor(engine, player.getId(), player.x, player.y, player.getPoints());
        entity.add(new OnlineComponent());
        addVirusComponents(entity, player);
        return entity;
    }

    public static Entity Loot(Engine engine, Loot loot) {
        Entity entity = BaseActor(engine, loot.getId(), loot.x, loot.y, loot.getPoints());
        entity.add(new LootComponent());
        entity.add(new OnlinePathComponent(loot.getPath()));
        entity.add(new TextureRegionComponent(AssetManager.getInstance().getTextureRegion("loot")));
        return entity;
    }
}
