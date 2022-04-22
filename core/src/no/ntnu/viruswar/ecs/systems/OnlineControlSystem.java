package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.OnlineComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;


public class OnlineControlSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> transformMapper;
    private final Array<Entity> entityQueue;
    private final ComponentMapper<IdentifierComponent> idMapper;
    private final LobbyController lobby;
    private final ComponentMapper<ConsumableComponent> sizeMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final Context context;
    private float timeSinceLastUpdate = Float.POSITIVE_INFINITY;

    public OnlineControlSystem(Context context, LobbyController lobby) {
        super(Family.all(TransformComponent.class, OnlineComponent.class, IdentifierComponent.class, ConsumableComponent.class, VelocityComponent.class).exclude(HiddenComponent.class, PlayerComponent.class).get());
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        sizeMapper = ComponentMapper.getFor(ConsumableComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

        this.lobby = lobby;
        this.context = context;
        entityQueue = new Array<>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (Entity entity : entityQueue) {
            TransformComponent trc = transformMapper.get(entity);
            IdentifierComponent idc = idMapper.get(entity);
            ConsumableComponent coc = sizeMapper.get(entity);
            VelocityComponent vcc = velocityMapper.get(entity);

            Player player = lobby.getPlayers().get(idc.id);

            trc.position.x = player.x;
            trc.position.y = player.y;
            vcc.velocity.set(new Vector3(player.getVelocityX(), player.getVelocityY(), 0));
            coc.size = player.getPoints();

            if (player.isConsumed()) {
                entity.add(new HiddenComponent());
            }

        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}

