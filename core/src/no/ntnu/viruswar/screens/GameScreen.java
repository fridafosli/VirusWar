package no.ntnu.viruswar.screens;

import com.badlogic.gdx.Gdx;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.OnlineEngine;
import no.ntnu.viruswar.services.lobby.LobbyController;

public class GameScreen extends ScreenContext {

    private final LobbyController lobby;
    private final OnlineEngine engine;

    public GameScreen(Context context, LobbyController controller) {
        super(context);
        this.lobby = controller;
        engine = new OnlineEngine(context, lobby);
        // Because of ECS logic is in ecs folder
    }


    @Override
    public void render(float dt) {
        engine.update(dt);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(engine.getInputProcessor());
    }

}

