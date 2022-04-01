package no.ntnu.viruswar;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import no.ntnu.viruswar.componenets.CircleComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;

public class EntityComparator implements Comparator<Entity> {

    private ComponentMapper<TextureComponent> textureMapper1;
    private ComponentMapper<TextureComponent> textureMapper2;


    @Override
    public int compare(Entity entity1, Entity entity2) {
        TextureComponent tc1 = textureMapper1.get(entity1);
        TextureComponent tc2 = textureMapper2.get(entity2);
        return tc1.zIndex - tc2.zIndex;
    }
}
