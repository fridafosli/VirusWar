package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionComponent implements Component {

    public TextureRegion region = null;
    public int zIndex = 100;
    public TextureRegionComponent(TextureRegion region) {
        this.region = region;
    }
}

