package model;

public abstract class Character {
    protected int xPosition;
    protected int yPosition;

    public Character(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public abstract void move(char direction, Maps map);
    public abstract boolean canMoveTo(int x, int y, Maps map);

    /**
     * Gets the x coordinate of the character.
     * 
     * @return the specific value of the x coordinate
     */
    public int getXPosition(){
        return this.xPosition;
    }
    
    /**
     * Gets the y coordinate of the character.
     * 
     * @return the specific value of the y coordinate
     */
    public int getYPosition(){
        return this.yPosition;
    }
}
