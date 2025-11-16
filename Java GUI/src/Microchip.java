/**
 * This represents the microchips that the player can collect in the game
 * 
 * <p>
 * All microchips placed in the map needs to be collected,
 * in order to exit the current level
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */

public class Microchip extends Item{
    /**
     * Constructs a new microchip at the specific coordinate.
     * 
     * post-condition: microchip is created at the given coordinates, isCollected set to false then symbol initialized as 'M'
     * @param xPosition represents x coordinate of microchip
     * @param yPosition represents y coordinate of microchip
     */
    public Microchip (int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }
    
    @Override
    public void onPlayerPickup(Player player){
        player.addMicrochips();
        this.setIsCollected(true);
    }

}
