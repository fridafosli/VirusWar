package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;


public class PlayerMovementSystem extends IteratingSystem {

    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<DimensionComponent> dimensionMapper;

    private Entity mapEntity;

    public PlayerMovementSystem(Entity mapEntity) {//Entity worldMap
        super(Family.all(TransformComponent.class, PlayerComponent.class, VelocityComponent.class).get());
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        dimensionMapper = ComponentMapper.getFor(DimensionComponent.class);

        this.mapEntity = mapEntity;
        entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        for (Entity entity : entityQueue) {
            DimensionComponent mapDimension = dimensionMapper.get(mapEntity);
            TransformComponent worldTc = transformMapper.get(mapEntity);
            float worldRadius = mapDimension.getRadius();

            DimensionComponent playerDimension = dimensionMapper.get(entity);
            VelocityComponent vcc = velocityMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);
            float playerRadius = playerDimension.getRadius();

            //Vector3 playerCenterPoint = new Vector3(trc.position.x + playerRadius, trc.position.y+playerRadius,0);
            //Vector3 worldCenterPoint = new Vector3(worldTc.position.x + worldRadius, worldTc.position.y + worldRadius,0);
            // ALLE METODER GIR SAMME AVSTAND
            //double d = Math.sqrt((playerCenterPoint.x-worldCenterPoint.x)*(playerCenterPoint.x-worldCenterPoint.x) + (playerCenterPoint.y-worldCenterPoint.y)*(playerCenterPoint.y-worldCenterPoint.y));
            //Vector3 centerToCenter = new Vector3(playerCenterPoint.x - worldCenterPoint.x, playerCenterPoint.y-worldCenterPoint.y, 0);
            //double dist = Math.hypot(Math.abs(playerCenterPoint.y-worldCenterPoint.y), Math.abs(playerCenterPoint.x-worldCenterPoint.x));
            //float distance = playerCenterPoint.cpy().sub(worldCenterPoint).len();
            //float distanceee = worldTc.position.cpy().sub(trc.position).len();
            //double j =  Math.hypot(playerCenterPoint.x-worldCenterPoint.x, playerCenterPoint.y-worldCenterPoint.y);


            /*
            int distSq = (int)Math.sqrt(((worldTc.position.x - trc.position.x)
                    * (worldTc.position.x - trc.position.x))
                    + ((worldTc.position.y - trc.position.y)
                    * (worldTc.position.y - trc.position.y)));

            // Det under begrenser spilleren til banen men får ikke relatert det til velocity
            if (distSq + playerRadius < worldRadius)
            {
                System.out.println("The smaller circle lies completely"
                        + " inside the bigger circle without"
                        + " touching each other "
                        + "at a point of circumference.") ;
            }*/



            if (vcc.velocity.len() > 0.1) {
                Vector3 tmp = new Vector3(trc.position);
                tmp.add(vcc.velocity);
                int d = (int)Math.sqrt(((worldTc.position.x - tmp.x)
                        * (worldTc.position.x - tmp.x))
                        + ((worldTc.position.y - tmp.y)
                        * (worldTc.position.y - tmp.y)));
                if ((d + playerRadius < worldRadius)){
                    trc.position.add(vcc.velocity.scl(deltaTime));
                }
                else {
                    int newD = d = (int)Math.sqrt(((worldTc.position.x - trc.position.x)
                            * (worldTc.position.x - trc.position.x))
                            + ((worldTc.position.y - trc.position.y)
                            * (worldTc.position.y - trc.position.y)));
                    Vector3 newVel = new Vector3(vcc.velocity);
                    newVel.setLength(worldRadius - newD - playerRadius);
                    trc.position.add(newVel.scl(deltaTime));
                }
            }
        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}


