package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.ntnu.viruswar.ecs.componenets.PlayerComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;
import no.ntnu.viruswar.ecs.utils.Camera;

public class CameraSystem extends IteratingSystem {
    private final ComponentMapper<TransformComponent> transformMapper;
    private Entity player;
    private Camera camera;

    public CameraSystem(Camera camera) {
        super(Family.all(TransformComponent.class, PlayerComponent.class).get());
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (player != null) {
            camera.setPosition(transformMapper.get(player).position);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        this.player = entity;
    }
}
