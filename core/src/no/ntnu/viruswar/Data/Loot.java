package no.ntnu.viruswar.Data;

public class Loot {

    private float x;
    private float y;
    private float points = 0;

    public Loot(int x, int y, int points) {
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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
}
