package no.ntnu.viruswar.context;

import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.screen.ScreenManager;

public class BuildContext implements Context {

    private final BackendService backend;
    private final ScreenManager screens;
    private final AssetManager assets;

    public BuildContext(BackendService backendService) {
        this.screens = new ScreenManager();
        this.assets = AssetManager.getInstance();
        this.backend = backendService;
    }

    public ScreenManager getScreens() {
        return screens;
    }

    public AssetManager getAssets() {
        return assets;
    }

    public BackendService getBackend() {
        return backend;
    }
}
