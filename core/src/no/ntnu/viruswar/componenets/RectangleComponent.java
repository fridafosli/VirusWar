package no.ntnu.viruswar.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class RectangleComponent implements Component {

    public Rectangle rect;

    public RectangleComponent(float x, float y, float width, float height) {
        this.rect = new Rectangle(x, y, width, height);
    }
}
