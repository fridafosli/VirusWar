package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.screens.EndScreen;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class GameStateSystem extends IteratingSystem {

    private Context context;
    private LobbyController controller;
    private Entity player;
    private final ComponentMapper<IdentifierComponent> idMapper;


    public GameStateSystem(Context context, LobbyController controller) {
        super(Family.all(PlayerComponent.class).get());
        idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        this.context = context;
        this.controller = controller;
    }
    @Override public void update(float deltaTime){
        super.update(deltaTime);
        IdentifierComponent idc = idMapper.get(player);
        Player p = controller.getPlayers().get(idc.id);
        // hvis man antar at isDead settes et annet sted når du blir
        // konsumert av noen:
        if (p.isLooser()){
            context.getScreens().push(new EndScreen(context, controller.getPin(), p, false));
        }
        else if (p.isWinner()){
            context.getScreens().push(new EndScreen(context, controller.getPin(), p, true));
        }
        // else iffen over der man antar at db variablen iswinner
        // settes et annet sted når alle andre er konsumert, eller:
        // else if controller.getPlayers.size == 1 push new winner screen
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.player = entity;
    }
}
