package no.ntnu.viruswar;


import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.models.BaseEntity;
import no.ntnu.viruswar.services.models.Loot;
import no.ntnu.viruswar.services.backend.BackendModel;
/*Methods not yet defined, but can be done in a similar manner to that found in the android folder*/
class DesktopBackendService implements BackendService {


    @Override
    public void startGame(String gamePin) {

    }

    @Override
    public void setEntityConsumedState(String gamePin, String path, String entityId, boolean state) {

    }

    @Override
    public void addEntityToGame(String gamePin, BaseEntity entity) {

    }

    @Override
    public void setLobbyStateListener(BackendModel dataHolder, String gamePin) {

    }

    @Override
    public void setGamePinEventListener(BackendModel dataHolder) {

    }

    @Override
    public void removeGame(String gamePin) {

    }

    @Override
    public void updatePlayerPosition(String gamePin, String playerId, float x, float y, float points) {

    }

    @Override
    public void removePlayerFromGame(String gamePin, String playerId) {

    }

    @Override
    public void setPlayersEventListener(BackendModel dataHolder, String gamePin) {

    }

    @Override
    public void addLootToGame(String gamePin, Loot loot) {

    }

    @Override
    public void removeLootFromGame(String gamePin, String lootId) {

    }

    @Override
    public void setLootEventListener(BackendModel dataHolder, String gamePin) {

    }

    @Override
    public void setPlayerSkin(String gamePin, String id, int skinIndex) {

    }

    @Override
    public void setNickname(String gamePin, String id, String nickname) {

    }
}

