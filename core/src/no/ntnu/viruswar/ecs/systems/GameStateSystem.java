package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.Map;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.screens.EndScreen;
import no.ntnu.viruswar.services.models.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class GameStateSystem extends IteratingSystem {

    private final Context context;
    private final LobbyController controller;
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
        Map<String, Player> players = controller.getPlayers();
        Player p = players.get(idc.id);

        if (p.consumed){
            context.getScreens().push(new EndScreen(context, controller.getPin(), p, false));
        }
        else {
            int count = 0;
            for (Player player: players.values()){
                if (!player.consumed){
                    count++;
                }
            }
            if (count == 1) {
                context.getScreens().push(new EndScreen(context, controller.getPin(), p, true));
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.player = entity;
    }
}

