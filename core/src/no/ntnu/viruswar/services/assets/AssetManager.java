package no.ntnu.viruswar.services.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;


public class AssetManager {

    static private AssetManager instance;

    private final HashMap<String, Texture> textureMap;
    private final Music music= Gdx.audio.newMusic(Gdx.files.internal("virusSong.mp3"));
    private float musicVolume;
    private Boolean play;
    private AssetManager() {
        textureMap = new HashMap<>();
        textureMap.put("virus", new Texture("virus.png"));
        textureMap.put("loot", new Texture("person.png"));
        textureMap.put("map", new Texture("hueCircle.png"));
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }
    public float getVolume(){
        return musicVolume;
    }
    public boolean getPlaying(){
        return play;
    }

    public void setMusic(boolean play, float volume){
        this.musicVolume=volume;
        this.play=play;
        music.setLooping(true);
        if(play){
            music.play();
            music.setVolume(volume);
        }
        else{
            music.stop();
        }


    };

    public Texture getTexture(String name) {
        return instance.textureMap.get(name);
    }

    public void dispose() {
        for (Texture texture : textureMap.values()) {
            texture.dispose();
        }
    }
}
