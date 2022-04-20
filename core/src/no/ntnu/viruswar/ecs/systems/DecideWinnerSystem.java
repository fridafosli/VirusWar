package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.screens.EndScreen;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class DecideWinnerSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;
    private final ComponentMapper<IdentifierComponent> idMapper;
    private  final LobbyController controller;

    public DecideWinnerSystem(Context context, LobbyController controller) {
        super(Family.all(PlayerComponent.class).exclude(HiddenComponent.class).get());
        entityQueue = new Array<>();
        this.context = context;
        this.controller = controller;
        idMapper = ComponentMapper.getFor(IdentifierComponent.class);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (entityQueue.size == 1) {
            IdentifierComponent idc = idMapper.get(entityQueue.get(0));
            Player player = controller.getPlayers().get(idc.id);
            context.getScreens().push(new EndScreen(context, player, true));

            // TODO: update something in firebase?

        }
        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
