package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;


public class PlayerMovementSystem extends IteratingSystem {

    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> renderQueue;

    public PlayerMovementSystem() {
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        renderQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : renderQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);
            RectangleComponent rtc = rectangleMapper.get(entity);

            if (vcc.velocity.len() > 0.1) {

                rtc.rect.setX(rtc.rect.x + vcc.velocity.x * deltaTime);
                rtc.rect.setY(rtc.rect.y + vcc.velocity.y * deltaTime);
            }

        }


//
//        for (Entity entity : renderQueue) {
//            RectangleComponent rtc  = rectangleMapper.get(entity);
//
//            Vector3 touch = touchController.getTouchInWorld();
//
//            if (!rtc.rect.contains(touch.x, touch.y)) {
//                continue;
//            }
//
//            rtc.rect.setPosition(touch.x - rtc.rect.width / 2, touch.y - rtc.rect.height / 2);
//
//
//
//        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}


