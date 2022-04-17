package no.ntnu.viruswar;


import no.ntnu.viruswar.context.Context;
import no.ntnu.viruswar.services.assets.AssetManager;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.data.Loot;
import no.ntnu.viruswar.services.data.NetworkDataController;
import no.ntnu.viruswar.services.data.Player;
import no.ntnu.viruswar.services.screen.ScreenManager;

class DesktopBackendService implements BackendService {


    @Override
    public void setGamePinEventListener(NetworkDataController dataHolder) {

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
    public void setPlayersEventListener(NetworkDataController dataHolder, String gamePin) {

    }

    @Override
    public void addLootToGame(String gamePin, Loot loot) {

    }

    @Override
    public void removeLootFromGame(String gamePin, String lootId) {

    }

    @Override
    public void setLootEventListener(NetworkDataController dataHolder, String gamePin) {

    }
}

