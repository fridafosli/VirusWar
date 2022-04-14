package no.ntnu.viruswar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera extends OrthographicCamera {

    final static float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();

    public Camera() {
        super(Constants.GAME_WORLD_WIDTH * aspectRatio, Constants.GAME_WORLD_HEIGHT);
        this.position.set(Constants.GAME_WORLD_WIDTH /2, Constants.GAME_WORLD_HEIGHT /2, 0);
    }

    public void setPosition(Vector3 position) {
        this.position.set(position);
    }

    public void zoom() {
        if (this.zoom > 0.7) {
            this.zoom += 0.001;
        }
    }
}
