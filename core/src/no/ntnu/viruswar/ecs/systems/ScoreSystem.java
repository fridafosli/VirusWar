package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.DimensionComponent;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.LeadTextComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.models.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.utils.Constants;

public class ScoreSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final ComponentMapper<IdentifierComponent> idMapper;

    private final LobbyController lobbyController;
    private LeadTextComponent textComp = new LeadTextComponent();
    private float leadpoints = 0;
    private String lead = "tie";
    private final Engine engine;

    public ScoreSystem(Engine engine, LobbyController lobbycontroller) {
        super(Family.all(PlayerComponent.class).exclude(HiddenComponent.class).get());
        this.lobbyController = lobbycontroller;
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        entityQueue = new Array<Entity>();
        this.engine = engine;
        Entity ent = engine.createEntity();
        ent.add(textComp);
        engine.addEntity(ent);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.leadpoints = 0;

        for (Player player: lobbyController.getPlayers().values()){
            if (player.getPoints() > leadpoints) {
                leadpoints = player.getPoints();
                lead = player.getName();
            }
        }
        /*
        for (Entity entity : entityQueue) {
            IdentifierComponent idc = idMapper.get(entity);
            Player p = lobbyController.getPlayers().get(idc.id);
            if (p.getPoints() > leadpoints) {
                leadpoints = p.getPoints();
                lead = p.getName();
            }
        }*/

        textComp.leadPlayer = "Lead: " + lead + " " + leadpoints + " points";

        entityQueue.clear();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}

