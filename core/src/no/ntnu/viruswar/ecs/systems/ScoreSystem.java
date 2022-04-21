package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.HiddenComponent;
import no.ntnu.viruswar.ecs.componenets.IdentifierComponent;
import no.ntnu.viruswar.ecs.componenets.LeadTextComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.backend.model.Player;
import no.ntnu.viruswar.services.lobby.LobbyController;
import no.ntnu.viruswar.utils.Constants;

public class ScoreSystem extends IteratingSystem {

    private final Array<Entity> entityQueue;
    private final Context context;
    private final ComponentMapper<PlayerComponent> playerMapper;
    private final ComponentMapper<IdentifierComponent> idMapper;
    private final ComponentMapper<TransformComponent> transMapper;
    private final LobbyController lobbyController;
    private LeadTextComponent textComp = new LeadTextComponent();
    private float leadpoints = 0;
    private String lead = "tie";
    private final Engine engine;

    public ScoreSystem(Engine engine, Context context, LobbyController lobbycontroller) {
        super(Family.all(PlayerComponent.class).exclude(HiddenComponent.class).get());
        this.context = context;
        this.lobbyController = lobbycontroller;
        this.playerMapper = ComponentMapper.getFor(PlayerComponent.class);
        this.idMapper = ComponentMapper.getFor(IdentifierComponent.class);
        this.transMapper = ComponentMapper.getFor(TransformComponent.class);
        entityQueue = new Array<Entity>();
        this.engine = engine;
        Entity ent = engine.createEntity();
        ent.add(textComp); // ta med position component også
        ent.add(new TransformComponent(Constants.GAME_WORLD_WIDTH / 2, Constants.GAME_WORLD_HEIGHT / 2));
        engine.addEntity(ent);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        this.leadpoints = 0;

        for (Entity entity : entityQueue) {
            IdentifierComponent idc = idMapper.get(entity);
            TransformComponent tc = transMapper.get(entity);
            PlayerComponent pc = playerMapper.get(entity);
            Player p = lobbyController.getPlayers().get(idc.id);
            textComp.position.x = 10;
            textComp.position.y = Constants.GAME_WORLD_HEIGHT;
            if (p.getPoints() > leadpoints) {
                leadpoints = p.getPoints();
                lead = p.getName();
            }
        }

        textComp.leadPlayer = "Lead: " + lead + leadpoints + "ps";

        entityQueue.clear();
    }

    public float getLeadpoints() {
        return leadpoints;
    }

    public String getLead() {
        return lead;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
