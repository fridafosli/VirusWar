package no.ntnu.viruswar.Data;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Player {

    private float x;
    private float y;
    private float points;
    private String skin;
    private String name;
    private String id;
    private String color;

    public Player() {
        // Default constructor required for calls to DataSnapshot.getValue(Player.class)
    }

    public Player(float x, float y, float points, String skin, String name) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.skin = skin;
        this.name = name;
        this.color= generateColor();
        id = UUID.randomUUID().toString();
    }

    public String generateColor(){
        List<Color> colors= Arrays.asList(Color.BLUE,Color.PINK,Color.CYAN, Color.RED, Color.GREEN, Color.MAGENTA, Color.BROWN,
                Color.FIREBRICK, Color.FOREST, Color.PURPLE, Color.CORAL, Color.LIME, Color.SKY,Color.ORANGE, Color.OLIVE,Color.YELLOW, Color.VIOLET, Color.WHITE,Color.GOLDENROD, Color.SALMON, Color.MAROON, Color.NAVY);
        int colorIndex= (int)(Math.random()*(colors.size()-1));
        return (colors.get(colorIndex)).toString();
    }

    public void setColor(String color){
        this.color=color;
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

    public String getColor(){
        return this.color;
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

    public String getId() {
        return id;
    }

    public String getSkin() {
        return skin;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
