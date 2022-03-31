package no.ntnu.viruswar.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class MapComponent implements Component {

    public Circle circle;
    private Texture mapTexture;
    public TextureRegion region;
    // NB LIBGDX HAS A CircleMapObject. checkout??
    // don't think it is necessary as the world is only a container and contains nothing else
    // radius same as game world length and height
    // can use setRadius or set to update position and radius
    // for instance x++, y++, r-- pr frame or something

    public MapComponent(){
        /*circle = new Circle(100,100,20);
        mapTexture = new Texture("hueCircle.png");
        region = new TextureRegion(mapTexture);
        region.setRegionHeight(10);
        region.setRegionX(0);
        region.setRegionY(0);*/

    }



}
