package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;

public class ConsumableComponent implements Component {

    public float size;

    public ConsumableComponent(float size) {
        this.size = size;
    }
}
