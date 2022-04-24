package no.ntnu.viruswar.ecs.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TouchController implements InputProcessor {

    private final Camera camera;
    public boolean isTouching;
    public Vector2 touchScreenLocation = new Vector2();
    private Vector3 calcVec3 = new Vector3();

    public TouchController(Camera camera) {
        this.camera = camera;
        Gdx.input.setInputProcessor(this);
        isTouching = false;
    }

    public Vector3 getTouchInWorld() {
        calcVec3 = calcVec3.set(touchScreenLocation, 0);
        camera.unproject(calcVec3);
        return calcVec3;
    }

    public Boolean touchQuitButton(float x, float y) {
        return !isTouching && calcVec3.x > x && calcVec3.y > y;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchScreenLocation.set(screenX, screenY);
        isTouching = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isTouching = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchScreenLocation.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

