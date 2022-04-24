package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.LeadTextComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.services.models.Player;

public class ScoreSystem extends IteratingSystem {

    private final LobbyController lobbyController;
    private final LeadTextComponent textComp = new LeadTextComponent();
    private String lead;

    public ScoreSystem(Engine engine, LobbyController lobbycontroller) {
        super(Family.all(PlayerComponent.class).exclude(HiddenComponent.class).get());
        this.lobbyController = lobbycontroller;
        Entity ent = engine.createEntity();
        ent.add(textComp);
        engine.addEntity(ent);
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float leadPoints = 0;

        for (Player p : lobbyController.getPlayers().values()) {
            if (p.getPoints() > leadPoints) {
                leadPoints = p.getPoints();
                lead = p.getName();
            }
        }
        if (leadPoints == 50) { //NB CHANGE TO 0 I POINTS CHANGED
            textComp.leadPlayer = "Tie";

        } else {
            textComp.leadPlayer = String.format("Lead: %s %.0f points", lead, leadPoints);
        }
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}

