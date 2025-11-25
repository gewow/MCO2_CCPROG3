package model;

/**
 * Represents the general items in the game that the player can collect.
 * 
 * <p>
 * Each item has their own positions, symbols, and collected status.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public abstract class Item {
    private int xPosition;
    private int yPosition;
    private boolean isCollected;
    private char symbol;

    /**
     * Constructs an Item with a specified position and symbol.
     * 
     * <p>
     * The item is not initially set as collected.
     * </p>
     * 
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     * @param symbol the character representing an item on the map.
     */
    public Item(int xPosition, int yPosition, char symbol){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.symbol = symbol;
        this.isCollected = false;
    }

    /**
     * Handles the effect when the player picks up an item ingame.
     *  
     * <p>
     * pre-condition: the player object is initialized and has a valid inventory
     * post-condition: the boots are added to the player's inventory and marked collected
     * </p>
     * @param player the player itself.
     */
    public abstract void onPlayerPickup(Player player);

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
