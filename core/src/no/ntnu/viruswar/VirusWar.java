package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.States.GameStateManager;
import no.ntnu.viruswar.States.MainMenu;

public class VirusWar extends ApplicationAdapter {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
//	public final Skin SKIN = new Skin(Gdx.files.internal("uiskin.json"));
//	public final float SCALE = Gdx.graphics.getHeight()/300;

    SpriteBatch batch;
    GameStateManager gsm;
    FireBaseInterface _FBIC;
    DataHolderClass dataHolder;
//	private Stage stage;
//	private Skin skin;

    public VirusWar(FireBaseInterface FBIC) {
        _FBIC = FBIC;

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager(_FBIC);
        gsm.push(new MainMenu(gsm));
        dataHolder = new DataHolderClass();
        //scale = Gdx.graphics.getHeight()/300;
        // skin = new Skin(Gdx.files.internal("uiskin.json"));
        //SKIN.getFont("default-font").getData().setScale(SCALE);
        gsm.render(batch);
        /*
        // Example of how to interact with th db
		// sets listeners
		_FBIC.setPlayersEventListener(dataHolder,"abc123");
		_FBIC.setPlayersEventListener(dataHolder, "def456");
		_FBIC.setLootEventListener(dataHolder, "abc123");
		_FBIC.setGamePinEventListener(dataHolder);
		// create player and loot
		Player player = new Player(1,2,3,"skin", "player_1");
		Player player2 = new Player(1,2,3,"skin", "player_2");
		Loot loot = new Loot(1,2,33);
		// adds player and loot a game in db
		_FBIC.addPlayerToGame("abc123", player);
		_FBIC.addPlayerToGame("abc123", player2);
		_FBIC.addLootToGame("abc123", loot);
		// removes player from game
		_FBIC.removePlayerFromGame("abc123", player.getId());
		// updates player by overwriting old one
		player.setPosition(22,33);
		_FBIC.addPlayerToGame("abc123", player);
		// updates player by its id
		_FBIC.updatePLayerPosition("abc123", "5a3320e7-0094-4aa1-a04c-201010b1760d", 200,200,799);
        */

    }

//		img = new Texture("badlogic.jpg");
//		gsm = new GameStateManager();
//		gsm.push(new MainMenu(gsm));
//		gsm.render(batch);


    @Override
    public void render() {
        // System.out.println("VIrus war "+ gsm.peek());
        gsm.render(batch);

//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		stage.act(Gdx.graphics.getDeltaTime());
//		stage.draw();
        //	ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
//		batch.dispose();
//		img.dispose();
    }
}
