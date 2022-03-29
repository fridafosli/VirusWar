package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import no.ntnu.viruswar.States.GameStateManager;
import no.ntnu.viruswar.States.MainMenu;

public class VirusWar extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
//	public final Skin SKIN = new Skin(Gdx.files.internal("uiskin.json"));
//	public final float SCALE = Gdx.graphics.getHeight()/300;

	SpriteBatch batch;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MainMenu(gsm));
		//scale = Gdx.graphics.getHeight()/300;
		// skin = new Skin(Gdx.files.internal("uiskin.json"));
		//SKIN.getFont("default-font").getData().setScale(SCALE);
	}

	@Override
	public void render () {
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
