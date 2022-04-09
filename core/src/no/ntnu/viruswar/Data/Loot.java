package no.ntnu.viruswar.Data;

import java.util.UUID;

public class Loot {

    private String id;
    private float x;
    private float y;
    private float points = 0;

    public Loot() {
        // Default constructor required for calls to DataSnapshot.getValue(Loot.class)
    }

    public Loot(float x, float y, float points) {
        id = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.points = points;
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

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Loot{" +
                "id='" + id + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", points=" + points +
                '}';
    }
}
