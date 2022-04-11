package no.ntnu.viruswar;


import no.ntnu.viruswar.Data.Loot;
import no.ntnu.viruswar.Data.Player;

public class CoreInterfaceClass implements FireBaseInterface {

    @Override
    public void setGamePinEventListener(DataHolderClass dataHolder) {

    }

    @Override
    public void addPlayerToGame(String gamePin, Player player) {

    }

    @Override
    public void updatePLayerPosition(String gamePin, String playerId, float x, float y, float points) {

    }

    @Override
    public void removePlayerFromGame(String gamePin, String playerId) {

    }

    @Override
    public void setPlayersEventListener(DataHolderClass dataHolder, String gamePin) {

    }

    @Override
    public void addLootToGame(String gamePin, Loot loot) {

    }

    @Override
    public void removeLootFromGame(String gamePin, String lootId) {

    }

    @Override
    public void setLootEventListener(DataHolderClass dataHolder, String gamePin) {

    }
}