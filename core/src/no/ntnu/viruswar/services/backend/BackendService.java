package no.ntnu.viruswar.services.backend;

import no.ntnu.viruswar.services.data.Loot;
import no.ntnu.viruswar.services.data.Player;
import no.ntnu.viruswar.services.data.NetworkDataController;

/**
 * Interface for communicating whit the database. Implementation in
 * android.java.no.ntnu.viruswar.AndroidInterfaceClqss
 */
public interface BackendService {
    /**
     * Stores all the active game-pins in dataHolder.activeGamePins. Is updated when a new game is
     * created or finished.
     * @param dataHolder where the data is stored
     */
    public void setGamePinEventListener(NetworkDataController dataHolder);

    /**
     * Adds player to game with the given game-pin. If game pin is not used, a new game is created.
     * @param gamePin The game pin to add the player to
     * @param player The player to be added to the game
     */
    public void addPlayerToGame(String gamePin, Player player);

    /**
     * Updates the players position and points in a game.
     * @param gamePin pin of the game
     * @param playerId id of the player
     * @param x new x-position
     * @param y new y-position
     * @param points new points
     */
    public void updatePLayerPosition(String gamePin, String playerId, float x, float y, float points);

    /**
     * Removes a player from a game.
     * @param gamePin pin of the game
     * @param playerId id of the player
     */
    public void removePlayerFromGame(String gamePin, String playerId);

    /**
     * Stores all players in a game in dataHolder.players. Is updated when players are added,
     * updated or removed.
     * @param dataHolder where the data is stored
     * @param gamePin game-pin of the game to be listened to
     */
    public void setPlayersEventListener(NetworkDataController dataHolder, String gamePin);

    /**
     * Adds loot to game with the given game-pin. If game pin is not used, a new game is created.
     * @param gamePin the game pin to add the loot to
     * @param loot The loot to be added to the game
     */
    public void addLootToGame(String gamePin, Loot loot);

    /**
     * Removes a loot from a game.
     * @param gamePin pin of the game
     * @param lootId id of the loot
     */
    public void removeLootFromGame(String gamePin, String lootId);

    /**
     * Stores all loot in a game in dataHolder.lootList. Is updated when loots are added or removed.
     * @param dataHolder where the data is stored
     * @param gamePin game-pin of the game to be listened to
     */
    public void setLootEventListener(NetworkDataController dataHolder, String gamePin);

}