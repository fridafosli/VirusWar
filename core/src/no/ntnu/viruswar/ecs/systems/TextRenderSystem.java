package no.ntnu.viruswar.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import no.ntnu.viruswar.ecs.componenets.LeadTextComponent;
import no.ntnu.viruswar.ecs.componenets.TransformComponent;

public class TextRenderSystem extends IteratingSystem {


    private final ComponentMapper<LeadTextComponent> leadMapper;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final Array<Entity> entityQueue;
    private final SpriteBatch batch;
    private final BitmapFont font = new BitmapFont();

    public TextRenderSystem(int priority, SpriteBatch batch) {
        super(Family.all(LeadTextComponent.class, TransformComponent.class).get(), priority);
        this.leadMapper = ComponentMapper.getFor(LeadTextComponent.class);
        this.transformMapper = ComponentMapper.getFor(TransformComponent.class);

        this.entityQueue = new Array<Entity>();
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        batch.begin();
        Entity entity = entityQueue.first();
        TransformComponent trc = transformMapper.get(entity);
        LeadTextComponent ltc = leadMapper.get(entity);
        this.font.draw(batch, ltc.leadPlayer, trc.position.x, trc.position.y);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
    }
}
