package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;

public class DimensionComponent implements Component {

    public float width;
    public float height;

    public DimensionComponent(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getRadius() {
        return Math.max(width, height) / 2;
    }

    public void setRadius(float radius) {
        this.width = radius * 2;
        this.height = radius * 2;
    }

    public void setSize(float size) {
        setRadius((float) Math.sqrt(size / MathUtils.PI));
    }

    public void add(float amount) {
        this.width = Math.max(0, this.width + amount);
        this.height = Math.max(0, this.height + amount);
    }
}
