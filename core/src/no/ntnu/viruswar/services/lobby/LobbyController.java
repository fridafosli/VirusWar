package no.ntnu.viruswar.services.lobby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Map;

import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.screens.GameLobby;
import no.ntnu.viruswar.screens.GameScreen;
import no.ntnu.viruswar.screens.PlayMenu;
import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.backend.model.Player;

//enum State {
//    MENU, CREATE, LOBBY, GAME
//}


public class LobbyController {

    private final Model model;
    private final Context context;
    private final BackendModel backendModel;
    

    public LobbyController(Context context) {
        this.context = context;
        this.model = new Model();
        this.backendModel = new BackendModel();
        context.getBackend().setGamePinEventListener(this.backendModel);

    }

    public Model getState() {
        return this.model;
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

        Player player = new Player(0, 0, 0, "blue", nick);
        model.set(false, pin, player.getId());
        context.getBackend().addPlayerToGame(pin, player);
        context.getBackend().setPlayersEventListener(this.backendModel, pin);
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

        System.out.println(pin);

        Player host = new Player(0, 0, 0, "default", nick);
        model.set(true, pin, host.getId());
        context.getBackend().addPlayerToGame(pin, host);
        context.getBackend().setPlayersEventListener(this.backendModel, pin);
        context.getScreens().push(new GameLobby(context, this));
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
        context.getBackend().startGame(model.getPin());
        context.getScreens().push(new GameScreen(context, this));
    }

    public void removePlayer() {
        context.getBackend().removePlayerFromGame(model.pin, model.playerId);
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

   public Map<String, Player> getPlayers() {
      return this.backendModel.getPlayers();
   }


  public String getPin() {
        return model.pin;
    }

  public Boolean isStarted() {
        return backendModel.isGameStarted;
  }
}
