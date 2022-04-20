package no.ntnu.viruswar.services.data;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// Gjorde den public, si fra dersom det er et problem
public class NetworkDataController {

    private final Collection<String> activeGamePins = new ArrayList<>();
    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Loot> lootList = new HashMap<>();

    public NetworkDataController() {

    }

    public void addActiveGamePin(String pin) {
        if (!activeGamePins.contains(pin))
            activeGamePins.add(pin);
    }

    public void removeActiveGamePin(String pin) {
        activeGamePins.remove(pin);
    }

    public boolean activeGamePinsContainsPin(String pin) {
        return activeGamePins.contains(pin);
    }

    public void addPlayer(float x, float y, float points) {
        Player p = new Player(x, y, points, "blue", "name");
        players.put(p.getId(), p);
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

    public void updatePlayer(String id, Player player) {
        players.put(id, player);
    }

    public Player getPlayer(String key) {
        if (players.containsKey(key))
            return players.get(key);
        return null;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public Array getPlayerNames() {
        Array playerlist = new Array();
        for (Map.Entry<String, Player> pl : this.players.entrySet()) {
            playerlist.add(pl.getValue().getName());
        }
        return playerlist;
    }

    public void removePlayer(String id) {
        players.remove(id);
    }

    public void addLoot(Loot loot) {
        this.lootList.put(loot.getId(), loot);
    }

    public Loot getLootById(String id) {
        return lootList.get(id);
    }

    /*
        public void updatePlayer(String id, Player player) {
            players.put(id, player);
        }

        public Player getPlayer(String key) {
            if (players.containsKey(key))
                return players.get(key);
            return null;
        }



        public void removePlayer(String id) {
            players.remove(id);
        }*/
    public Map<String, Loot> getLootList() {
        return lootList;
    }

}

