package no.ntnu.viruswar.managers;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;


public class AssetManager {

    static private AssetManager instance;

    private final HashMap<String,Texture> textureMap;

    private AssetManager() {
        textureMap = new HashMap<>();
        textureMap.put("virus", new Texture("badlogic.jpg"));
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public Texture getTexture(String name) {
        return instance.textureMap.get(name);
    }

    public void dispose() {
        for (Texture texture : textureMap.values()) {
            texture.dispose();
        }
    }
}
