package no.ntnu.viruswar.services.backend;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.viruswar.services.models.Loot;
import no.ntnu.viruswar.services.models.Player;

public class BackendModel {

    private final Collection<String> activeGamePins = new ArrayList<>();
    private final Map<String, Player> players = new HashMap<>();
    private final Map<String, Loot> loots = new HashMap<>();
    public boolean isGameStarted = false;

    public BackendModel() {

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

    public void addPlayer(float x, float y, float points, String name) {
        // TODO: Set skin index
        Player p = new Player(x, y, points, 0, name);
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
        this.loots.put(loot.getId(), loot);
    }

    public Loot getLootById(String id) {
        return loots.get(id);
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
    public Map<String, Loot> getLoots() {
        return loots;
    }

}

