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

public class DecideLooserSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;
    private  final LobbyController controller;
    private final ComponentMapper<IdentifierComponent> idMapper;


    public DecideLooserSystem(Context context, LobbyController controller) {
        super(Family.all(PlayerComponent.class, HiddenComponent.class).get());
        idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        entityQueue = new Array<>();
        this.context = context;
        this.controller = controller;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (Entity entity: entityQueue){
            // every player in this queue is now one that has been consumed
            // push the endscreen to each of them
            IdentifierComponent idc = idMapper.get(entity);
            Player player = controller.getPlayers().get(idc.id);
            context.getScreens().push(new EndScreen(context, player, false));
            // TODO: update something in firebase?
        }


        entityQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
