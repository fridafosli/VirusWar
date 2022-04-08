package no.ntnu.viruswar.Data;

import java.util.UUID;

public class Player {

    private float x;
    private float y;
    private float points;
    private String skin;
    private String name;
    private String id;

    public Player() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Player(float x, float y, float points, String skin, String name) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.skin = skin;
        this.name = name;
        id = UUID.randomUUID().toString();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
    public String getName() {
        return name;
    }
    public String getId(){
        return id;
    }
}
