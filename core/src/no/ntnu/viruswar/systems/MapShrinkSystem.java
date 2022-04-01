package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Texture;
//sortetd iterating system in renderingsystem

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.MapComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class MapShrinkSystem extends IntervalSystem {

    private final ComponentMapper<DimensionComponent> dimensionMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    //NEEDS FIXING WHEN WORLD COORDINATES AND CAMERA ARE GOOD
    //EDIT SHRINK METHOD^^

    private Entity entity;
    //private Texture worldTexture;
    //private boolean created = false;

    public MapShrinkSystem(float interval, Entity entity) {
        super(interval);
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.entity = entity;
        //worldTexture = new Texture("hueCircle.png");
    }

    /*private Entity createWorld(){
        entity = getEngine().createEntity();
        DimensionComponent rc = new DimensionComponent(Constants.GAME_WORLD_WIDTH, Constants.GAME_WORLD_HEIGHT); //update to actual size
        TextureComponent txc = new TextureComponent();
        MapComponent mc = new MapComponent();
        TransformComponent tc = new TransformComponent(0,0);
        txc.region = worldTexture;
        txc.zIndex = 0;
        entity.add(mc);
        entity.add(rc);
        entity.add(txc);
        entity.add(tc);
        return entity;
    }*/

    private void shrink(){

        DimensionComponent dc = dimensionMapper.get(entity);
        TransformComponent tc = transformMapper.get(entity);
        //uncomment under here
/*
        dc.add(-10);
        tc.position.add(10);*/

    }

    @Override
    protected void updateInterval() {
        /*if (!created){
            getEngine().addEntity(createWorld());
            created = true;
        }
        else {
            System.out.println("i am here"); //for debug. remove
            shrink();
        }*/
        shrink();
    }

}
