package no.ntnu.viruswar.services.backend.model;

import com.badlogic.gdx.math.Vector3;

import java.util.UUID;

abstract public class BaseEntity {

    private boolean consumed;
    private String id;
    private String path;
    public float x;
    public float y;
    private float velocityX;
    private float velocityY;
    private float size;

    public BaseEntity() {
        // Default constructor required for calls to DataSnapshot.getValue(BaseEntity.class)
    }

    protected BaseEntity(String path, float x, float y, float size) {
        this.id = UUID.randomUUID().toString();
        this.path = path;
        this.x = x;
        this.y = y;
        this.velocityX = 0;
        this.velocityY = 0;
        this.size = size;
        this.consumed = false;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPos(Vector3 pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void setPoints(float size) {
        this.size = size;
    }

    public float getPoints() {
        return size;
    }

    public void setConsumed(boolean state) {
        consumed = state;
    }

    public boolean isConsumed() {
        return consumed;
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
        return String.format("{ id=%s, x=%.2f, y=%.2f, size=%.2f }", id, x, y, size);
    }

}
