package no.ntnu.viruswar.ecs.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import no.ntnu.viruswar.ecs.componenets.TextureComponent;

public class EntityComparator implements Comparator<Entity> {

    private final ComponentMapper<TextureComponent> textureMapper;

    public EntityComparator() {
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
    }

    @Override
    public int compare(Entity entity1, Entity entity2) {
        TextureComponent tc1 = textureMapper.get(entity1);
        TextureComponent tc2 = textureMapper.get(entity2);
        return tc1.zIndex - tc2.zIndex;
    }
}

