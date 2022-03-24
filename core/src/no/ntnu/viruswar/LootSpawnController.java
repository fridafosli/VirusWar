package no.ntnu.viruswar;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class LootSpawnController  {

    // deciding when and where to spawn loot

    //private int waitFrames; //can be decremented each update
    //private Vector3 position;

    //public LootSpawnController(){
    //}

    private int getRandom(float max){
        Random random = new Random();
        if (max == 0){
            return random.nextInt(2400-600)+600; //assuming 60fps: range 10-40s
        }
        return random.nextInt((int) max); //evt floor
    }

    private Vector3 generatePosTime() {
        int x = getRandom(Constants.GAME_WORLD_WIDTH);
        int y = getRandom(Constants.GAME_WORLD_HEIGHT);
        int waitFrames = getRandom(0);
        return new Vector3(x, y, waitFrames);

    }
    /*
     public int getTime(){
        return waitFrames;
     }
     public void decrementWait(){

     }*/
}
