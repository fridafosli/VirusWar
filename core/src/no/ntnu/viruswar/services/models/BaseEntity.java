package no.ntnu.viruswar.services.models;

import java.util.UUID;

// Basic interface for loot and player to extend
abstract public class BaseEntity {

    public float x;
    public float y;
    public boolean consumed;
    private String id;
    private String path;
    private float velocityX;
    private float velocityY;
    private float points;

    public BaseEntity() {
        // Default constructor required for calls to DataSnapshot.getValue(BaseEntity.class)
    }

    protected BaseEntity(String path, float x, float y, float points) {
        this.id = UUID.randomUUID().toString();
        this.path = path;
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.points = points;
        this.consumed = false;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public String toString() {
        return String.format("{ id=%s, x=%.2f, y=%.2f, size=%.2f }", id, x, y, points);
    }

}
