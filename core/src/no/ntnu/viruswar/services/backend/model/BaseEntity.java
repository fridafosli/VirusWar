package no.ntnu.viruswar.services.backend.model;

import com.badlogic.gdx.math.Vector3;

import java.util.UUID;

abstract public class BaseEntity {

    private String id;
    private String name;
    public float x;
    public float y;
    private float size;

    public BaseEntity() {
        // Default constructor required for calls to DataSnapshot.getValue(Loot.class)
    }

    protected BaseEntity(String name, float x, float y, float size) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    @SuppressWarnings("DefaultLocale")
    @Override
    public String toString() {
        return String.format("{ id=%s, x=%.2f, y=%.2f, size=%.2f }", id, x, y, size);
    }

}
