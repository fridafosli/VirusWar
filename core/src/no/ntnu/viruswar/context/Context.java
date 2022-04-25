package no.ntnu.viruswar.context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.screen.ScreenManager;

/*Interface for getting data from Service classes*/
public interface Context {
    ScreenManager getScreens();

    AssetManager getAssets();

    BackendService getBackend();

    SpriteBatch getBatch();

    //TODO: dispose
}
