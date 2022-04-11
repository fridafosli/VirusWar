package no.ntnu.viruswar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.viruswar.Data.Loot;
import no.ntnu.viruswar.Data.Player;

public class DataHolderClass {

    private Collection<String> activeGamePins = new ArrayList<>();
    private Map<String, Player> players = new HashMap<>();
    private Map<String, Loot> lootList = new HashMap<>();

    public DataHolderClass() {

    }

    public void addActiveGamePin(String pin) {
        if (!activeGamePins.contains(pin))
            activeGamePins.add(pin);
    }

    public void removeActiveGamePin(String pin) {
        if (activeGamePins.contains(pin))
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

