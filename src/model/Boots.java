package model;

/**
 * This represents the boots object that the player can collect in the game.
 * 
 * <p>
 * Each type of boots provide a special quirk and
 * this keeps track of its position on the map, the type of boots, the symbol, and if it's collected or not
 * </p>
 * 
 * @author Anton Galido
 * @version 1.0
 * 
 */

public class Boots extends Item{
    private String type;
    /**
     * Constructs a Boots object with a specified x and y position, type of boots, and its symbol.
     * 
     * @param xPosition the x coordinate of the Boots
     * @param yPosition the y coordinate of the Boots
     * @param type the type of boots
     * @param symbol the symbol representing with each type of boots
     */
    public Boots(int xPosition, int yPosition, char symbol, String type){
        super(xPosition, yPosition, symbol);
        this.type = type;
    }
    
    @Override
    public void onPlayerPickup(Player player){
        player.getInventory().addBoots(this);
        this.setIsCollected(true);
    }

   
    /**
     * Returns the type of boots (Either Flippers or fireboots).
     * 
     * <p>
     * pre-condition: Boots should be instantiated
     * post-condition: returns type of boots as a String
     * </p>
     * @return type of boots
     */
    public String getType(){
        return type;
    }
}