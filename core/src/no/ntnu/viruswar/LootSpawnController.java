package no.ntnu.viruswar;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class  LootSpawnController {

    // deciding when and where to spawn loot
    // NOT IN USE


    private int getRandom(float max) {
        Random random = new Random();
        return random.nextInt((int) max); //evt floor
    }

    public Vector3 generateSpawnPos() {
        int width = getRandom(Constants.GAME_WORLD_WIDTH);
        int height = getRandom(Constants.GAME_WORLD_WIDTH);
        Random random = new Random();
        int x = random.nextInt((int)Constants.GAME_WORLD_WIDTH);
        int y = random.nextInt((int)Constants.GAME_WORLD_WIDTH);
        return new Vector3(x, y, 0);
    }
}

