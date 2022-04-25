package no.ntnu.viruswar;

import com.badlogic.gdx.ApplicationAdapter;

import no.ntnu.viruswar.context.BuildContext;
import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.screens.MainMenuScreen;
import no.ntnu.viruswar.services.backend.BackendService;


public class VirusWar extends ApplicationAdapter {
    Context context;
    BackendService backendService;

    public VirusWar(BackendService backendService) {
        this.backendService = backendService;
    }

    @Override
    public void create() {
        context = new BuildContext(backendService);
        context.getScreens().push(
                new MainMenuScreen(context));
    }

    @Override
    public void render() {
        context.getScreens().nextFrame();
    }

    @Override
    public void dispose() {
        context.getAssets().dispose();
    }
}