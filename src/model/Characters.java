package model;

/**
 * Represents a moving character in the game (Enemy or the Player).
 * 
 * <p>
 * Gets the positions and defines the movement related behavior regardless of character type.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 * 
 */
public abstract class Characters {
    protected int xPosition;
    protected int yPosition;

    /**
     * Creates a character at the specified coordinates.
     * 
     * @param xPosition x coordinate of the character.
     * @param yPosition y coordinate of the character.
     */
    public Characters(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Moves the character in a given direction if they're allowed.
     * 
     * @param direction the target direction.
     * @param map the map used to validate the movement.
     */
    public abstract void move(char direction, Maps map);

    /**
     * Checks if they are allowed to move to a specific location.
     * 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param map the map being used to validate the movement.
     * @return true if allowed, false if not.
     */
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
