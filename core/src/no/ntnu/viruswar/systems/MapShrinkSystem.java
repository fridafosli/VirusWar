package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.objects.TextureMapObject;


import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.CircleComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;

public class MapShrinkSystem extends IntervalSystem {

    private final ComponentMapper<CircleComponent> mapMapper;
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<CircleComponent> circleMapper;

    //private final SpriteBatch batch;
    //private final Array<Entity> renderQueue;
    // sjekk ut image isten for texture


    //create and add in screenSystem?
    private Texture mapTexture;
    private TextureRegion region;
    private TextureMapObject mapObject;
    private Entity entity;
    private Texture worldTexture;
    private boolean created = false;



    public MapShrinkSystem(float interval) {
        super(interval);
        //entity = new Entity();
        mapMapper = ComponentMapper.getFor(CircleComponent.class);
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        circleMapper = ComponentMapper.getFor(CircleComponent.class);

        worldTexture = new Texture("hueCircle.png");

        //this.batch = batch;
        //mapTexture = new Texture("hueCircle.png");
        //region = new TextureRegion(mapTexture);
        //mapObject = new TextureMapObject(region);
    }

    private Entity createWorld(){
        entity = getEngine().createEntity();
        RectangleComponent rc = new RectangleComponent(200,200, Constants.GAME_WORLD_WIDTH, Constants.GAME_WORLD_HEIGHT); //update to actual size
        TextureComponent txc = new TextureComponent();
        CircleComponent cc = new CircleComponent(rc.rect.x, rc.rect.y, rc.rect.width);
        txc.region = worldTexture;
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
        /*System.out.println(Integer.toString(renderQueue.size));
        for (Entity entity: renderQueue){
            //MapComponent mp = mapMapper.get(entity);
            //TextureComponent tc = textureMapper.get(entity);
            //RectangleComponent rc = rectangleMapper.get(entity);
            //rc.rect.set(rc.rect.x, rc.rect.y, rc.rect.width - 10, rc.rect.height - 10);
            //tc.tr.setRegion(rc.rect.x + 1, rc.rect.y + 1, rc.rect.width - 1, rc.rect.height - 1);
            System.out.println("made it here" );

            //mp.circle.set(mp.circle.x + 1, mp.circle.y + 1, mp.circle.radius -1);
            //mp.region.setRegion(mp.circle.x + 1, mp.circle.y + 1, mp.circle.radius -1, mp.circle.radius -1);
        }*/



    }

    /*@Override
    protected void processEntity(Entity entity) {
        renderQueue.add(entity);
    }*/
}
