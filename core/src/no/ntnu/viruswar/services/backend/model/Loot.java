package no.ntnu.viruswar.services.backend.model;

public class Loot extends BaseEntity {

    // Default constructor required for calls to DataSnapshot
    public Loot(){};

    public Loot(float x, float y, float size) {
        super("loot", x, y, size);
    }

}
