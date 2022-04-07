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
        Vector3 worldCenterPoint = new Vector3(worldTc.position.x + worldRadius, worldTc.position.y + worldRadius,0);

            DimensionComponent playerDimension = dimensionMapper.get(entity);
            VelocityComponent vcc = velocityMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);

            float playerRadius = playerDimension.getRadius();
            Vector3 playerCenterPoint = new Vector3(trc.position.x + playerRadius, trc.position.y+playerRadius,0);

            // ALLE METODER GIR SAMME AVSTAND
            //double d = Math.sqrt((playerCenterPoint.x-worldCenterPoint.x)*(playerCenterPoint.x-worldCenterPoint.x) + (playerCenterPoint.y-worldCenterPoint.y)*(playerCenterPoint.y-worldCenterPoint.y));
            Vector3 centerToCenter = new Vector3(playerCenterPoint.x - worldCenterPoint.x, playerCenterPoint.y-worldCenterPoint.y, 0);
            double dist = Math.hypot(Math.abs(playerCenterPoint.y-worldCenterPoint.y), Math.abs(playerCenterPoint.x-worldCenterPoint.x));
            float distance = playerCenterPoint.cpy().sub(worldCenterPoint).len();
            //float distanceee = worldTc.position.cpy().sub(trc.position).len();
            double j =  Math.hypot(playerCenterPoint.x-worldCenterPoint.x, playerCenterPoint.y-worldCenterPoint.y);



            //det er noe galt med distance. den er alt for stor
            //tror egt dette er ish riktig tanke men blir fucka pga banen og koordinatene
            //kanskje fordi pixlene er så dratt horisontalt at avstanden blir lenger

            System.out.println("world rad: " + Float.toString(worldRadius));
            System.out.println("player rad: " + Float.toString(playerRadius));
            System.out.println("world center: " + Double.toString(worldCenterPoint.x) + ", " + Double.toString(worldCenterPoint.y));
            System.out.println("player center: " + Double.toString(playerCenterPoint.x) + ", " + Double.toString(playerCenterPoint.y));
            System.out.println("C2C: "+Double.toString(centerToCenter.len()));
            System.out.println("dist: " + Double.toString(dist));
            System.out.println("distanceee: " + Double.toString(distance));
            System.out.println("j: " + Double.toString(j));

            int distSq = (int)Math.sqrt(((worldTc.position.x - trc.position.x)
                    * (worldTc.position.x - trc.position.x))
                    + ((worldTc.position.y - trc.position.y)
                    * (worldTc.position.y - trc.position.y)));

            if (distSq + playerRadius == worldRadius)
            {
                System.out.println("The smaller circle lies completely"
                        + " inside the bigger circle with "
                        + "touching each other "
                        + "at a point of circumference. ") ;
            }

            else if (distSq + playerRadius < worldRadius)
            {
                System.out.println("The smaller circle lies completely"
                        + " inside the bigger circle without"
                        + " touching each other "
                        + "at a point of circumference.") ;
            }
            else
            {
                System.out.println("The smaller does not lies inside"
                        + " the bigger circle completely.") ;
            }

            if (vcc.velocity.len() > 0.1 && (distSq + playerRadius < worldRadius)) {
                trc.position.add(vcc.velocity.scl(deltaTime));
            }
        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}


