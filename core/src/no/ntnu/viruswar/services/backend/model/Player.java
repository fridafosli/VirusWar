package no.ntnu.viruswar.services.backend.model;

public class Player extends BaseEntity {


    private String skin;
    private String name;
    private float velocityX;
    private float velocityY;

    // Default constructor required for calls to DataSnapshot
    public Player(){};

    public Player(float x, float y, float points, String skin, String name) {
        super("players", x, y, points);
        this.name = name;
        this.skin = skin;
        velocityX = 0;
        velocityY = 0;
    }

    public String getSkin() {
        return skin;
    }

    public String getName() { return name; }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }


}
