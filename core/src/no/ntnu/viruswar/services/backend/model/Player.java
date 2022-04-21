package no.ntnu.viruswar.services.backend.model;

public class Player extends BaseEntity {

    private String skin;
    private boolean isWinner;
    private boolean isLooser;

    // Default constructor required for calls to DataSnapshot
    public Player(){};

    public Player(float x, float y, float points, String skin, String name) {
        super(name, x, y, points);
        this.skin = skin;
        isWinner = false;
        isLooser = false;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public boolean isLooser() {
        return isLooser;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
