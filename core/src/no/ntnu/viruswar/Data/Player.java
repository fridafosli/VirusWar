package no.ntnu.viruswar.Data;

import com.badlogic.gdx.graphics.Color;

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
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0x10) + 0x10;
        System.out.printf("%x\n",myRandomNumber);
        return Integer.toHexString(myRandomNumber);
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
