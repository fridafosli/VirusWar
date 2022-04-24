package no.ntnu.viruswar.services.models;

public class Player extends BaseEntity {

    private String skin;
    private String name;

    // Default constructor required for calls to DataSnapshot
    public Player() {
    }

    public Player(float x, float y, float points, String skin, String name) {
        super("players", x, y, points);
        this.name = name;
        this.skin = skin;

    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
