package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import no.ntnu.viruswar.managers.AssetManager;
import no.ntnu.viruswar.managers.screen.ScreenManager;
import no.ntnu.viruswar.screens.GameLobby;
import no.ntnu.viruswar.screens.GameScreen;


public class VirusWar extends ApplicationAdapter {
	FireBaseInterface _FBIC;
	SpriteBatch batch;
	ScreenManager screenManager;

	public VirusWar(FireBaseInterface FBIC){
		_FBIC=FBIC;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		screenManager = new ScreenManager();
		screenManager.push(new GameLobby(screenManager));
	}

	@Override
	public void render () {
		screenManager.nextFrame();

	}

	@Override
	public void dispose () {
		batch.dispose();
		AssetManager.getInstance().dispose();
	}
}
