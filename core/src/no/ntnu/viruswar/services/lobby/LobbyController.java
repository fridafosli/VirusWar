package no.ntnu.viruswar.services.lobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Map;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.ecs.factories.ActorFactory;
import no.ntnu.viruswar.screens.GameLobby;
import no.ntnu.viruswar.screens.GameScreen;
import no.ntnu.viruswar.screens.PlayMenu;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.models.Loot;
import no.ntnu.viruswar.services.models.Player;

public class LobbyController {

    private final LobbyModel lobbyModel;
    private final Context context;
    private final BackendModel backendModel;


    public LobbyController(Context context) {
        this.context = context;
        this.lobbyModel = new LobbyModel();
        this.backendModel = new BackendModel();
        context.getBackend().setGamePinEventListener(this.backendModel);

    }

    static void displayText(String message, final Label output, final long duration) {
        output.setText(message);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(duration * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    output.setText("");

                }
            }
        };
        timer.start();
    }

    public LobbyModel getState() {
        return this.lobbyModel;
    }

    public void joinLobby(String pin, String nick, Label label) {
        if (pin.length() < 1 && nick.length() < 1) {
            displayText("\n Please fill gamepin and nickname to join.", label, 5);
            return;
        }

        if (!backendModel.activeGamePinsContainsPin(pin)) {
            displayText("Gamepin not valid", label, 5);
            return;
        }

        Vector2 pos = ActorFactory.randomPos(100);
        Player player = new Player(pos.x, pos.y, 30, "blue", nick);
        lobbyModel.set(false, pin, player.getId());
        context.getBackend().addEntityToGame(pin, player);
        subscribeToEntities(pin);
        context.getScreens().push(new GameLobby(context, this));
        context.getBackend().setLobbyStateListener(this.backendModel, getPin());
    }


    public void createLobby(String nick, Label label) {
        if (nick.length() < 1) {
            displayText("Please fill nickname to create game.", label, 5);
            return;
        }

        // Generate a random 6 character game-pin
        int pinLength = 6;
        String pin = PinGenerator.generate(pinLength);

        // If the game-pin already exists, a new one is created
        while (backendModel.activeGamePinsContainsPin(pin)) {
            pin = PinGenerator.generate(pinLength);
        }

        Player host = new Player(0, 0, 0, "default", nick);
        context.getBackend().addEntityToGame(pin, host);
        lobbyModel.set(true, pin, host.getId());
        subscribeToEntities(pin);
        context.getScreens().push(new GameLobby(context, this));
    }

    void subscribeToEntities(String pin) {
        context.getBackend().setPlayersEventListener(this.backendModel, pin);
        context.getBackend().setLootEventListener(this.backendModel, pin);
    }

    void toMenu() {
        this.context.getBackend().setGamePinEventListener(this.backendModel);
        context.getScreens().push(new PlayMenu(context));
    }

    public void toGame(final Label output) {
        if (backendModel.getPlayers().values().size() < 1) { // < 1 for testing
            displayText("Cannot start game without opponents", output, 5);
            return;
        }
        Gdx.app.log("play", "clicked");
        context.getBackend().startGame(lobbyModel.getPin());
        context.getScreens().push(new GameScreen(context, this));
    }

    public void removePlayer() {
        context.getBackend().removePlayerFromGame(lobbyModel.pin, lobbyModel.playerId);
    }

    public Map<String, Player> getPlayers() {
        return this.backendModel.getPlayers();
    }

    public Player getUserPlayer() {
        return this.backendModel.getPlayer(this.getState().playerId);
    }

    public Map<String, Loot> getLoots() {
        return this.backendModel.getLoots();
    }

    public boolean entityIsConsumed(String entityId) {
        return (getLoots().containsKey(entityId) && getLoots().get(entityId).consumed ||
                getPlayers().containsKey(entityId) && getPlayers().get(entityId).consumed);
    }


    public String getPin() {
        return lobbyModel.pin;
    }

    public Boolean isStarted() {
        return backendModel.isGameStarted;
    }
}
