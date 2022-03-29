package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class RenderingSystem extends IteratingSystem {

    private final SpriteBatch batch;
    private final Array<Entity> renderQueue;
    private final OrthographicCamera camera;

    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<RectangleComponent> rectangleMapper;

    public RenderingSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(RectangleComponent.class, TextureComponent.class).get());

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        rectangleMapper = ComponentMapper.getFor(RectangleComponent.class);

        renderQueue = new Array<Entity>();

        this.camera = camera;
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent texture = textureMapper.get(entity);
            RectangleComponent rtc = rectangleMapper.get(entity);

            if (texture.region == null) {
                continue;
            }

            float cx = rtc.rect.x - rtc.rect.width;
            float cy = rtc.rect.y - rtc.rect.height;
            batch.draw(texture.region, cx, cy, rtc.rect.width, rtc.rect.height);
        }

        batch.end();
        renderQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}