package no.ntnu.viruswar.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.Camera;
import no.ntnu.viruswar.EntityComparator;
import no.ntnu.viruswar.TouchController;
import no.ntnu.viruswar.componenets.PlayerComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;
import no.ntnu.viruswar.systems.LootSpawnSystem;
import no.ntnu.viruswar.systems.MapShrinkSystem;
import no.ntnu.viruswar.systems.PlayerControlSystem;
import no.ntnu.viruswar.systems.PlayerMovementSystem;
import no.ntnu.viruswar.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private PooledEngine engine;
    private final SpriteBatch batch;
    private final Texture texture;
    private final Camera camera;
    private final TouchController touchController;

    public GameScreen(SpriteBatch batch) {
        super();
        this.batch = batch;
        texture = new Texture("badlogic.jpg");
        camera = new Camera();
        touchController = new TouchController(camera);
        Gdx.input.setInputProcessor(touchController);
    }

    private void init() {
        engine = new PooledEngine();
        engine.addSystem(new PlayerControlSystem(touchController));
        engine.addSystem(new PlayerMovementSystem());
        EntityComparator comparator = new EntityComparator();
        engine.addSystem(new RenderingSystem(batch, camera, comparator));
        engine.addSystem(new LootSpawnSystem(20));
        engine.addSystem(new MapShrinkSystem(10));
        engine.addEntity(createVirus());
    }

    private Entity createVirus() {
        Entity entity = engine.createEntity();
        TransformComponent tfc = new TransformComponent();
        tfc.position.set(0f, 0f, 0f);
        entity.add(tfc);
        TextureComponent txc= new TextureComponent();
        txc.region = texture;
        entity.add(txc);
        RectangleComponent rtc = new RectangleComponent(50, 50, 40, 40);
        entity.add(rtc);
        entity.add(new VelocityComponent());
        entity.add(new PlayerComponent());
        return entity;
    }

    private void update(float dt) {
        engine.update(dt);
    }

    @Override
    public void render(float dt) {
        if (engine == null) {
            init();
        }
        update(dt);
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }
}

