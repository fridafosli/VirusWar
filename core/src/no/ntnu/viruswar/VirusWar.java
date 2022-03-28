package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
public class VirusWar extends ApplicationAdapter {
	SpriteBatch batch;
	GameStateManager gsm;
	FireBaseInterface _FBIC;
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

		gsm.render(batch);
		_FBIC.SomeFunction();
		_FBIC.FirstFireBaseTest();
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
//		batch.dispose();
//		img.dispose();
	}
}
