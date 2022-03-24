package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.TouchController;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;


public class PlayerControlSystem extends IteratingSystem {

    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> renderQueue;
    private final TouchController touchController;

    public PlayerControlSystem(TouchController touchController) {
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        renderQueue = new Array<Entity>();
        this.touchController = touchController;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : renderQueue) {
            VelocityComponent vcc = velocityMapper.get(entity);
            RectangleComponent rtc = rectangleMapper.get(entity);


            if (!touchController.isTouching) {
                vcc.velocity.set(0, 0);
            } else {
                Vector3 touch = touchController.getTouchInWorld();
                float cx = rtc.rect.x - rtc.rect.width / 2;
                float cy = rtc.rect.y - rtc.rect.height / 2;

                Vector2 vel = new Vector2(touch.x - cx, touch.y - cy).scl(0.005f);
                vel.clamp(0, 2);
//                float length = vel.len();
//                vel.nor();
                vcc.velocity.set(vel);


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

