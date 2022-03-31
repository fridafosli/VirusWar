package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.componenets.HiddenComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class RenderingSystem extends IteratingSystem {

    private final SpriteBatch batch;
    private final Array<Entity> entityQueue;
    private final OrthographicCamera camera;

    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(RectangleComponent.class, TextureComponent.class, TransformComponent.class).exclude(HiddenComponent.class).get());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        entityQueue = new Array<Entity>();

        this.camera = camera;
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Entity entity : entityQueue) {
            TextureComponent texture = textureMapper.get(entity);
            RectangleComponent rtc = rectangleMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);

            if (texture.region == null) {
                continue;
            }

            batch.draw(texture.region, trc.position.x - rtc.rect.width / 2, trc.position.y - rtc.rect.height / 2, rtc.rect.width, rtc.rect.height);
        }

        batch.end();
        entityQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }

}