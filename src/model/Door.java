package model;

/**
 * This represents the Door that the player can interact (by unlocking using a key) in the game
 * 
 * <p>
 * Each door is compatible with a specific key 
 * and so the player can only enter the door if the player has a key compatible with the door
 * </p>
 * 
 * @author Anton Galido
 * @version 1.0
 */

public class Door {
    private int xPosition;
    private int yPosition;
    private boolean isLocked;
    private String color;
    private char symbol;
    
    /**
     * Constructs sa new door object with a specified coordinate, color and symbol
     * 
     * @param xPosition the x coordinate of the Door
     * @param yPosition the y coordinate of the Door
     * @param color the represented color of the door
     * @param symbol the symbol or character representing the door in the map
     */
    public Door (int xPosition, int yPosition, String color, char symbol) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isLocked = true;
        this.color = color;
        this.symbol = symbol;
    }
    
    /**
     * Checks if the player can enter through the door.
     * 
     * <p>
     * The player can enter through the door if they have the proper key
     * or if it's already unlocked.
     * </p>
     * 
     * pre-condition: player object exists and has a valid inventory
     * post-condition: returns true if door is unlocked or player has a key, false otherwise
     * 
     * @param player the player who is attempting to enter the door
     * 
     * @return true if player can enter, false otherwise
     */
    public boolean canPlayerEnter(Player player) {
        // if color is red/blue, check if inventory has any one of those, if so, return true, else false

        if(isLocked == false) {
            return true;
        }
        
        Inventory inventory = player.getInventory();

        if (inventory.hasKey(this.color)) {
            return true;  // Player has the key
        }

        System.out.println("Door is locked! You need a " + this.color + " key to unlock it!");
        return false;
    }
    
    /**
     * Handles what happens once player enters the door.
     * 
     * <p>
     * If the player has the compatible key, they enter
     * as the door unlocks, then the door tile will be replaced by 
     * a blank tile.
     * </p>
     * 
     * pre-condition: player and map objects exist
     * post-condition: if player can enter, door is unlocked, key is consumed, and is replaced with blank tile
     * 
     * @param player the player who interacts with the door
     * @param map the map where the door resides
     */
    public void onPlayerEnter(Player player, Maps map) {

        Inventory inventory = player.getInventory();
        
        if (canPlayerEnter(player) == true) {
            if (this.isLocked) {  // Only if actually locked
                this.isLocked = false;
                inventory.useKey(this.color);
                System.out.println("Door unlocked using player's " + this.color + " key!");
            }
            Tile tile = new FloorTile(this.xPosition, this.yPosition, '.');
            map.setTile(this.xPosition, this.yPosition, tile);
        }

    }
    
    /**
     * returns the x coordinate of the door.
     * 
     * @return xPosition
     */
    public int getXPosition() {
        return xPosition;
    }
    
    /**
     * returns the y coordinate of the door.
     * 
     * @return yPosition
     */
    public int getYPosition() {
        return yPosition;
    }
    
    /**
     * returns specific color of the door.
     * 
     * @return color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * returns the value of isLocked (true if locked, false otherwise).
     * @return isLocked
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * returns the character symbol of the door.
     * @return symbol
     */
    public char getSymbol() {
        return symbol;
    }
}
