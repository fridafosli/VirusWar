package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.componenets.ConsumableComponent;
import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class BackendSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<ConsumableComponent> sizeMapper;
    private Entity entity;
    private final Context context;
    private final LobbyController controller;

    public BackendSystem(Context context, LobbyController controller) {
        super(Family.all(TransformComponent.class, PlayerComponent.class, ConsumableComponent.class).get());
        this.controller = controller;
        this.context = context;
        this.sizeMapper = ComponentMapper.getFor(ConsumableComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        Vector3 pos = transformMapper.get(entity).position;
        float size = sizeMapper.get(entity).size;
        context.getBackend().updatePlayerPosition(controller.getState().getPin(), controller.getState().getPlayerId(),pos.x, pos.y, size);


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.entity = entity;
    }
}
