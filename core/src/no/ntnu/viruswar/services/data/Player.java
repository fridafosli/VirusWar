package no.ntnu.viruswar.services.data;

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
        List<String> colors= Arrays.asList("v1", "v2", "v3", "v4", "v5", "v6", "v7");
        int colorIndex= (int)(Math.random()*(colors.size()-1));
        return colors.get(colorIndex);
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

    public void setName(String name){
        this.name=name;
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
