package no.ntnu.viruswar.systems;

import com.badlogic.ashley.systems.IntervalSystem;

import java.util.Random;

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.factories.LootFactory;

public class LootSpawnSystem extends IntervalSystem {

    // May want to rename class: if the loot should have a constant placement
    // "on the map" to the background

    /*private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper;
    private final ComponentMapper<LootComponent> lootComponentComponentMapper;
    private final ComponentMapper<TextureComponent> textureComponentComponentMapper;*/

    //private final LootSpawnController lootSpawnController;
    // Has no velocity, static with respect to background



    public LootSpawnSystem(float interval) {
        super(interval);
    }

    @Override
    protected void updateInterval() {
        System.out.println("spawning");
        Random random = new Random();
        int x = random.nextInt((int) Constants.GAME_WORLD_WIDTH);
        //consider making loot spawn within a given radius of the client that generates it.
        int y = random.nextInt((int)Constants.GAME_WORLD_WIDTH);
        getEngine().addEntity(LootFactory.createEntity(getEngine(), x, y)); //when consumed: remove entity
    }

}
