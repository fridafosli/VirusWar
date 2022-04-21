package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.VelocityComponent;
import no.ntnu.viruswar.services.data.NetworkDataController;

public class ScoreSystem extends SortedIteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;
    private final ComponentMapper<PlayerComponent> playerMapper;
    private final NetworkDataController dataHolder = new NetworkDataController();
    private final String pin;

    public ScoreSystem(Family family, Comparator<Entity> comparator, int priority, Context context, String pin) {
        super(family.all(PlayerComponent.class).get(), comparator, priority);
        this.context = context;
        this.playerMapper = ComponentMapper.getFor(PlayerComponent.class);
        this.pin = pin;
        context.getBackend().setPlayersEventListener(this.dataHolder, this.pin);
        entityQueue = new Array<Entity>();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        int leadpoints = 0;
        String lead = "";
        for (Entity entity : entityQueue) {
            PlayerComponent p = playerMapper.get(entity);
            if (p.points > leadpoints) {
                leadpoints = p.points;
                lead = p.getClass().getName();
            }
        }

        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
