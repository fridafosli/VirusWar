package no.ntnu.viruswar.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {

    public Texture region = null;
    public int zIndex = 100;
}
