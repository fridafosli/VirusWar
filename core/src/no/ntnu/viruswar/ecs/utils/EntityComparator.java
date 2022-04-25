package no.ntnu.viruswar.ecs.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import no.ntnu.viruswar.ecs.componenets.TextureRegionComponent;

/*Compares the z indexes for two entities*/
public class EntityComparator implements Comparator<Entity> {

    private final ComponentMapper<TextureRegionComponent> textureMapper;

    public EntityComparator() {
        textureMapper = ComponentMapper.getFor(TextureRegionComponent.class);
    }

    @Override
    public int compare(Entity entity1, Entity entity2) {
        TextureRegionComponent tc1 = textureMapper.get(entity1);
        TextureRegionComponent tc2 = textureMapper.get(entity2);
        return tc1.zIndex - tc2.zIndex;
    }
}

