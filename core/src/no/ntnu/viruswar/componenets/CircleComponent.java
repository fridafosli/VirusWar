package no.ntnu.viruswar.componenets;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;

public class CircleComponent implements Component {
//lag en circle component i steden. sett rectangle rundt hele bildet
// or sirkel rundt selve rundingen.
    //må ha: rectangle, circle, texture for å være i render
    //shrink: mink rect og circle.
    //collision: circle overlap med player rect.

    public Circle circle;
    // NB LIBGDX HAS A CircleMapObject. checkout??
    // don't think it is necessary as the world is only a container and contains nothing else
    // radius same as game world length and height
    // can use setRadius or set to update position and radius
    // for instance x++, y++, r-- pr frame or something

    public CircleComponent(float x, float y, float rad){
        this.circle = new Circle(x, y, rad);
        /*circle = new Circle(100,100,20);
        mapTexture = new Texture("hueCircle.png");
        region = new TextureRegion(mapTexture);
        region.setRegionHeight(10);
        region.setRegionX(0);
        region.setRegionY(0);*/

    }



}
