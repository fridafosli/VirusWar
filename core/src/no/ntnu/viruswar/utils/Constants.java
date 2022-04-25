package no.ntnu.viruswar.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/*Constants used several places in the code*/
public class Constants {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static String TITLE = "Virus War";
    public static float GAME_WORLD_HEIGHT = 200;
    public static float GAME_WORLD_WIDTH = 200;
    public static float SCREEN_HEIGHT_SCALE = Gdx.graphics.getHeight() / 100f;
    public static float SCREEN_WIDTH_SCALE = Gdx.graphics.getWidth() / 100f;
    public static float FONT_SCALE = Gdx.graphics.getHeight() / 300f;
    public static Vector2 GAME_WORLD_CENTER = new Vector2(GAME_WORLD_WIDTH / 2, GAME_WORLD_HEIGHT / 2);
}

