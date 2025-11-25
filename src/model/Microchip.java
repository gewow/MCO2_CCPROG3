package model;

/**
 * This represents the microchips that the player can collect in the game
 * 
 * <p>
 * All microchips placed in the map needs to be collected,
 * in order to exit the current level
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.1
 */

public class Microchip extends Item{
    /**
     * Constructs a new microchip at the specific coordinate.
     * 
     * post-condition: microchip is created at the given coordinates, isCollected set to false then symbol initialized as 'M'
     * @param xPosition represents x coordinate of microchip
     * @param yPosition represents y coordinate of microchip
     * @param symbol char representing the microchip itself.
     */
    public Microchip (int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }
    
    /**
     * Handles when the player picks up microchip
     * 
     * <p>
     * adds the microchip then sets it as collected.
     * </p>
     * 
     * @param player the player itself.
     */
    @Override
    public void onPlayerPickup(Player player){
        player.addMicrochips();
        this.setIsCollected(true);
    }

}
