package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.States.GameStateManager;
import no.ntnu.viruswar.States.MainMenu;

public class VirusWar extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;

	SpriteBatch batch;
	private GameStateManager gsm;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MainMenu(gsm));
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
