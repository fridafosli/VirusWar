package no.ntnu.viruswar.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.awt.TextComponent;
import java.util.Random;

import no.ntnu.viruswar.Constants;
import no.ntnu.viruswar.LootSpawnController;
import no.ntnu.viruswar.componenets.LootComponent;
import no.ntnu.viruswar.componenets.RectangleComponent;
import no.ntnu.viruswar.componenets.TextureComponent;
import no.ntnu.viruswar.componenets.TransformComponent;
import no.ntnu.viruswar.componenets.VelocityComponent;

public class LootSpawnSystem extends IntervalSystem {

    // May want to rename class: if the loot should have a constant placement
    // "on the map" to the background

    /*private final ComponentMapper<RectangleComponent> rectangleMapper;
    private final ComponentMapper<TransformComponent> transformComponentComponentMapper;
    private final ComponentMapper<LootComponent> lootComponentComponentMapper;
    private final ComponentMapper<TextureComponent> textureComponentComponentMapper;*/
    private final Texture lootTexture;

    //private final LootSpawnController lootSpawnController;
    // Has no velocity, static with respect to background



    public LootSpawnSystem(float interval) {
        super(interval);
        lootTexture = new Texture("badlogic.jpg");    // change to loot texture
    }

    @Override
    protected void updateInterval() {
        Random random = new Random();
        int x = random.nextInt((int) Constants.GAME_WORLD_WIDTH);
        int y = random.nextInt((int)Constants.GAME_WORLD_WIDTH);
        getEngine().addEntity(createLoot(x, y)); //when consumed: remove entity
    }

    private Entity createLoot(int x, int y){
        Entity entity = getEngine().createEntity();
        TransformComponent tfc = new TransformComponent();
        tfc.position.set(x, y, 0);
        LootComponent lc = new LootComponent(); // set points or same point for all?
        RectangleComponent rc = new RectangleComponent(x, y, 20, 20); //update to actual texture size
        TextureComponent txc = new TextureComponent();
        txc.region = lootTexture;
        entity.add(tfc);
        entity.add(lc);
        entity.add(rc);
        entity.add(txc);
        return entity;
    }
}
