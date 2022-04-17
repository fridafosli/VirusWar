package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class BackendSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> transformMapper;
    private Entity entity;
    private final Context context;
    private final LobbyController controller;

    public BackendSystem(Context context, LobbyController controller) {
        super(Family.all(TransformComponent.class, PlayerComponent.class).get());
        this.controller = controller;
        this.context = context;
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        Vector3 pos = transformMapper.get(entity).position;
        context.getBackend().updatePlayerPosition(controller.getState().getPin(), controller.getState().getPlayerId(),pos.x, pos.y, 5);


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.entity = entity;
    }
}
