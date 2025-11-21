package model;

public class Enemy {
    private int xPosition;
    private int yPosition;
    private char direction;

    public Enemy(int xPosition, int yPosition, char direction) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
    }
}