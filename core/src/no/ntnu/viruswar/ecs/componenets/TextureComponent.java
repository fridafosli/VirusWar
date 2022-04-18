package no.ntnu.viruswar.ecs.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {

    public Texture region = null;
    public int zIndex = 100;
}

