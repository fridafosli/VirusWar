package no.ntnu.viruswar.services.backend;

import no.ntnu.viruswar.services.models.BaseEntity;
import no.ntnu.viruswar.services.models.Loot;

/**
 * Interface for communicating whit the database. Implementation in
 * android.java.no.ntnu.viruswar.AndroidInterfaceClqss
 */
public interface BackendService {
    /**
     * Stores all the active game-pins in dataHolder.activeGamePins. Is updated when a new game is
     * created or finished.
     *
     * @param dataHolder where the data is stored
     */
    void setGamePinEventListener(BackendModel dataHolder);

    /**
     * Sets the game state to started
     */
    void startGame(String gamePin);


    void setEntityConsumedState(String gamePin, String path, String entityId, boolean state);


    /**
     * Adds loot to game with the given game-pin. If game pin is not used, a new game is created.
     *
     * @param gamePin the game pin to add the loot to
     * @param entity  The entity to be added to the game
     */
    void addEntityToGame(String gamePin, BaseEntity entity);

    /**
     * Updates the players position and points in a game.
     *
     * @param gamePin  pin of the game
     * @param playerId id of the player
     * @param x        new x-position
     * @param y        new y-position
     * @param points   new points
     */
    void updatePlayerPosition(String gamePin, String playerId, float x, float y, float points);

    void setLobbyStateListener(final BackendModel dataHolder, String gamePin);

    /**
     * Removes a player from a game.
     *
     * @param gamePin  pin of the game
     * @param playerId id of the player
     */
    void removePlayerFromGame(String gamePin, String playerId);

    /**
     * Stores all players in a game in dataHolder.players. Is updated when players are added,
     * updated or removed.
     *
     * @param dataHolder where the data is stored
     * @param gamePin    game-pin of the game to be listened to
     */
    void setPlayersEventListener(BackendModel dataHolder, String gamePin);

    /**
     * Adds loot to game with the given game-pin. If game pin is not used, a new game is created.
     *
     * @param gamePin the game pin to add the loot to
     * @param loot    The loot to be added to the game
     */
    void addLootToGame(String gamePin, Loot loot);

    /**
     * Removes a loot from a game.
     *
     * @param gamePin pin of the game
     * @param lootId  id of the loot
     */
    void removeLootFromGame(String gamePin, String lootId);

    /**
     * Stores all loot in a game in dataHolder.lootList. Is updated when loots are added or removed.
     *
     * @param dataHolder where the data is stored
     * @param gamePin    game-pin of the game to be listened to
     */
    void setLootEventListener(BackendModel dataHolder, String gamePin);

    void setPlayerSkin(String gamePin, String id, int skinIndex);

    void setNickname(String gamePin, String id, String nickname);

}