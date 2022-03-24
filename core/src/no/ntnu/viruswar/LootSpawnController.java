package no.ntnu.viruswar;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class LootSpawnController  {

    // deciding when and where to spawn loot

    private int nextSpawn;    //"timer": frames until next, assuming 60fps

    private int getRandom(float max){
        Random random = new Random();
        if (max == 0){
            return random.nextInt(2400-600)+600; //assuming 60fps: range 10-40s
        }
        return random.nextInt((int) max); //evt floor
    }

    public Vector3 generateSpawnPos() {
        if (this.nextSpawn > 0){
            return null; //denied
        }
        //only allowed if randomly generated time has passed
        int x = getRandom(Constants.GAME_WORLD_WIDTH);
        int y = getRandom(Constants.GAME_WORLD_HEIGHT);
        this.nextSpawn = getRandom(0);
        return new Vector3(x, y, 0);

    }
     // call from render/update
     public void decrementWait(){
        this.nextSpawn--;
     }
}
