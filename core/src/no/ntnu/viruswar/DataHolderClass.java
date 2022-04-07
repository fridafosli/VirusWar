package no.ntnu.viruswar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import no.ntnu.viruswar.Data.Player;

class DataHolderClass {

    String someValue;
    private Map<String, Player> players = new HashMap<>();

    public DataHolderClass() {

    }

    public void addPlayer(float x, float y, float points) {
        Player p = new Player(x, y,points, "blue", "name");
        players.put(p.getId(), p);
    }

    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

    public void updatePlayer(String id, Player player) {
        players.put(id, player);
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void removePlayer(String id) {
        players.remove(id);
    }

    public void PrintSomeValue() {
        System.out.println("from printSomeValue: " + someValue);
    }

}
