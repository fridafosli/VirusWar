package no.ntnu.viruswar.context;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.screen.ScreenManager;

public class BuildContext implements Context {

    private final BackendService backend;
    private final ScreenManager screens;
    private final AssetManager assets;
    private final SpriteBatch batch;

    public BuildContext(BackendService backendService) {
        this.screens = new ScreenManager();
        this.assets = AssetManager.getInstance();
        this.backend = backendService;
        this.batch = new SpriteBatch();
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

    public SpriteBatch getBatch() {
        return batch;
    }
}
