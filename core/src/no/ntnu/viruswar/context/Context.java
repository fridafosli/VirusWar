package no.ntnu.viruswar.context;

import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.screen.ScreenManager;

public interface Context {
    ScreenManager getScreens();
    AssetManager getAssets();
    BackendService getBackend();

    //TODO: dispose
}
