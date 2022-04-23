package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.LeadTextComponent;
import no.ntnu.viruswar.ecs.utils.Camera;

public class TextRenderSystem extends IteratingSystem {

    private final ComponentMapper<LeadTextComponent> leadMapper;
    private final Array<Entity> entityQueue;
    private final SpriteBatch batch;
    private final BitmapFont font = new BitmapFont();
    private final Camera camera;

    public TextRenderSystem(int priority, SpriteBatch batch, Camera camera) {
        super(Family.all(LeadTextComponent.class).get(), priority);
        this.leadMapper = ComponentMapper.getFor(LeadTextComponent.class);
        this.camera = camera;
        this.entityQueue = new Array<Entity>();
        this.batch = batch;
        //font.getData().setScale(0.7f, 0.7f);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Entity entity = entityQueue.first();
        LeadTextComponent ltc = leadMapper.get(entity);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        this.font.draw(batch, ltc.leadPlayer, camera.position.x + 5 -camera.viewportWidth/2, camera.position.y-5+camera.viewportHeight/2);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}

