package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import no.ntnu.viruswar.managers.AssetManager;
import no.ntnu.viruswar.managers.ScreenManager;
import no.ntnu.viruswar.screens.GameScreen;

public class VirusWar extends ApplicationAdapter {
	SpriteBatch batch;
	ScreenManager screenManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		screenManager = new ScreenManager();
		screenManager.push(new GameScreen(screenManager));
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
