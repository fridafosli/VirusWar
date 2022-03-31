package no.ntnu.viruswar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import no.ntnu.viruswar.screens.GameScreen;

public class VirusWar extends Game {
	SpriteBatch batch;
	Screen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		setScreen(gameScreen);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
