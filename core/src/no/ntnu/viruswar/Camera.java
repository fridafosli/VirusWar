package no.ntnu.viruswar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {

    final static float aspectRatio = (float) Gdx.graphics.getHeight() /  (float) Gdx.graphics.getWidth();

    public Camera() {
        super(Constants.GAME_WORLD_WIDTH * aspectRatio, Constants.GAME_WORLD_WIDTH * aspectRatio);
        this.position.set(Constants.GAME_WORLD_WIDTH /2, Constants.GAME_WORLD_HEIGHT /2, 0);
    }
}
