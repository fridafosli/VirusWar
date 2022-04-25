package no.ntnu.viruswar.services.models;

/*Creates a player entity, includes skin and username in addition to what is taken in as parameters in supeclass*/
public class Player extends BaseEntity {

    private int skinIndex;
    private String name;

    // Default constructor required for calls to DataSnapshot
    public Player() {
    }

    public Player(float x, float y, float points, int skinIndex, String name) {
        super("players", x, y, points);
        this.name = name;
        this.skinIndex = skinIndex;

    }

    public int getSkinIndex() {
        return skinIndex;
    }

    public void setSkinIndex(int skinIndex) {
        this.skinIndex = skinIndex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
