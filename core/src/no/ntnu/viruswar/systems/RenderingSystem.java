package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.EntityComparator;
import no.ntnu.viruswar.componenets.HiddenComponent;
import no.ntnu.viruswar.componenets.DimensionComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;

public class RenderingSystem extends SortedIteratingSystem {

    private final SpriteBatch batch;
    private final Array<Entity> entityQueue;
    private final OrthographicCamera camera;
    private final EntityComparator comparator;

    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<DimensionComponent> rectangleMapper;
    private final ComponentMapper<TransformComponent> transformMapper;

    public RenderingSystem(SpriteBatch batch, Camera camera, EntityComparator comparator) {
        super(Family.all(DimensionComponent.class, TextureComponent.class, TransformComponent.class).exclude(HiddenComponent.class).get(), comparator);


        this.comparator = comparator;
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        rectangleMapper = ComponentMapper.getFor(DimensionComponent.class);
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
            DimensionComponent rtc = rectangleMapper.get(entity);
            TransformComponent trc = transformMapper.get(entity);

            if (texture.region == null) {
                continue;
            }

            batch.draw(texture.region, trc.position.x - rtc.width / 2, trc.position.y - rtc.height / 2, rtc.width, rtc.height);
        }

        batch.end();
        entityQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
        entityQueue.sort(comparator);
    }

}