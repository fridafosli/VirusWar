package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;

public class DecideWinnerSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;

    public DecideWinnerSystem(Context context) {
        super(Family.all(PlayerComponent.class).get());
        entityQueue = new Array<>();
        this.context = context;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (entityQueue.size == 1) {
            // TODO
            // push end screen to this players screen
            // set as winner in db?
            // remove player from game in db?
            //context.getScreens().push(SETT INN END SCREEN);


        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
