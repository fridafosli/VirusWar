package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;
//sortetd iterating system in renderingsystem

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.CircleComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;

public class MapShrinkSystem extends IntervalSystem {

    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<CircleComponent> circleMapper;

    //private final SpriteBatch batch;
    //private final Array<Entity> renderQueue;
    // sjekk ut image isten for texture


    //create and add in screenSystem?

    private Entity entity;
    private Texture worldTexture;
    private boolean created = false;

    public MapShrinkSystem(float interval) {
        super(interval);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        circleMapper = ComponentMapper.getFor(CircleComponent.class);
        worldTexture = new Texture("hueCircle.png");
    }

    private Entity createWorld(){
        entity = getEngine().createEntity();
        RectangleComponent rc = new RectangleComponent(200,200, Constants.GAME_WORLD_WIDTH, Constants.GAME_WORLD_HEIGHT); //update to actual size
        TextureComponent txc = new TextureComponent();
        CircleComponent cc = new CircleComponent(rc.rect.x, rc.rect.y, rc.rect.width);
        txc.region = worldTexture;
        txc.zIndex = 0;
        entity.add(cc);
        entity.add(rc);
        entity.add(txc);
        return entity;
    }

    private void shrink(){
        RectangleComponent rc = rectangleMapper.get(entity);
        CircleComponent cc = circleMapper.get(entity);
        rc.rect.set(rc.rect.x + 10, rc.rect.y +10, rc.rect.width - 10, rc.rect.height - 10);
        cc.circle.set(rc.rect.x, rc.rect.y, rc.rect.width);
    }

    @Override
    protected void updateInterval() {
        // get the entity and edit its size components (circle)
        if (!created){
            getEngine().addEntity(createWorld());
            created = true;
        }
        else {
            System.out.println("i am here");
            shrink();
        }
    }

}
