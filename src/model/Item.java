package model;

public abstract class Item {
    private int xPosition;
    private int yPosition;
    private boolean isCollected;
    private char symbol;

    public Item(int xPosition, int yPosition, char symbol){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.symbol = symbol;
        this.isCollected = false;
    }

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
