package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;

public class DecideLooserSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;

    public DecideLooserSystem(Context context) {
        super(Family.all(PlayerComponent.class, HiddenComponent.class).get());
        entityQueue = new Array<>();
        this.context = context;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // TODO: implement changing screens
        // every player in this queue is now one that has been consumed
        // push the endscreen to each of them

        // set Firebase variable consumed. when set, new screen push new endScreen (looser)
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
