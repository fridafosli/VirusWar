package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {

    public final Vector3 position;

    public TransformComponent(float x, float y) {
        position = new Vector3(x, y, 0);
    }
}

