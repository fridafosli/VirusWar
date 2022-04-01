package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Texture;
//sortetd iterating system in renderingsystem

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.CircleComponent;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.TextureComponent;

public class MapShrinkSystem extends IntervalSystem {

    private final ComponentMapper<DimensionComponent> rectangleMapper;
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
        rectangleMapper = ComponentMapper.getFor(DimensionComponent.class);
        circleMapper = ComponentMapper.getFor(CircleComponent.class);
        worldTexture = new Texture("hueCircle.png");
    }

    private Entity createWorld(){
        entity = getEngine().createEntity();
        DimensionComponent rc = new DimensionComponent(Constants.GAME_WORLD_WIDTH, Constants.GAME_WORLD_HEIGHT); //update to actual size
        TextureComponent txc = new TextureComponent();
        CircleComponent cc = new CircleComponent(rc.width, rc.height, rc.width);
        txc.region = worldTexture;
        entity.add(cc);
        entity.add(rc);
        entity.add(txc);
        return entity;
    }

    private void shrink(){
        DimensionComponent rc = rectangleMapper.get(entity);
        CircleComponent cc = circleMapper.get(entity);
        rc.add(-10);
        cc.circle.set(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT / 2, rc.width);
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
