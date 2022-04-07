package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.Data.Player;
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

	public VirusWar(FireBaseInterface FBIC){
		_FBIC=FBIC;

	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MainMenu(gsm));
		dataHolder = new DataHolderClass();

		//scale = Gdx.graphics.getHeight()/300;
		// skin = new Skin(Gdx.files.internal("uiskin.json"));
		//SKIN.getFont("default-font").getData().setScale(SCALE);

		gsm.render(batch);
		_FBIC.SomeFunction();
		_FBIC.FirstFireBaseTest();
		_FBIC.SetOnValueChangedListener(dataHolder);
		Player player = new Player(1,2,3,"skin", "Erlem");
		Player player2 = new Player(1,2,3,"skin", "erlen");
		_FBIC.AddPlayerToGame("abc123",player);
		_FBIC.SetChildEventListener(dataHolder,"abc123");
		_FBIC.AddPlayerToGame("abc123",player2);
		_FBIC.RemovePlayerFromGame("abc123", player.getId());


	}

//		img = new Texture("badlogic.jpg");
//		gsm = new GameStateManager();
//		gsm.push(new MainMenu(gsm));
//		gsm.render(batch);


	@Override
	public void render () {
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
	public void dispose () {
		batch.dispose();
//		batch.dispose();
//		img.dispose();
	}
}
