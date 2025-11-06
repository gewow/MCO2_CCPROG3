/**
 * This represents the key that the players can interact and use in the game
 * 
 * <p>
 * Each type of key is compatible with a specific door
 * and so the player can use that key to unlock of a door of the same specified color
 * </p>
 * 
 * @author Anton galido
 * @author Melangelo Guanzon
 * 
 * @version 1.0
 */

public class Key{
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isCollected;
    private char symbol;

    /**
     * Constructs a key with a specified coordinate, color and symbol for representation
     * 
     * post-condition: Key object is created with given coordinates, color, symbol, and isCollected set to false
     * 
     * @param xPosition the x coordinate of the Key
     * @param yPosition the y coordinate of the key
     * @param color the color of the key
     * @param symbol the symbol representing each key type
     */
    public Key(int xPosition, int yPosition, String color, char symbol){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.isCollected = false;
        this.symbol = symbol; 
    }

    /**
     * Returns the color of the key.
     * 
     * @return color
     */
    public String getColor(){
        return color;
    }

    /**
     * Returns the x coordinate of the key.
     * 
     * @return xPosition
     */
    public int getXPosition(){
        return xPosition;
    }

    /**
     * Returns the y coordinate of the key.
     * 
     * @return yPosition
     */
    public int getYPosition(){
        return yPosition;
    }
    /**
     * Returns the symbol representing the key.
     * 
     * @return symbol
     */
    public char getSymbol(){
        return symbol;
    }

    /**
     * <p>
     * Returns true or false, depending on whether the key has been collected
     * by the player.
     * </p>
     * 
     * @return isCollected (true if collected, false otherwise)
     */
    public boolean getIsCollected(){
        return isCollected;
    }

    /**
     * Sets the collected status of the key
     * 
     * @param isCollected (true if key has been collected, false otherwise)
     * 
     */
    public void setIsCollected(boolean isCollected){
        this.isCollected = isCollected;
    }
}