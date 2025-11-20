package model;

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

public class Key extends Item{
    private String color;
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
    public Key(int xPosition, int yPosition, char symbol, String color){
        super(xPosition, yPosition, symbol);
        this.color = color;
    }

    @Override
    public void onPlayerPickup(Player player){
        player.getInventory().addKey(this);
        this.setIsCollected(true);
    }

    /**
     * Returns the color of the key.
     * 
     * @return color
     */
    public String getColor(){
        return color;
    }
}