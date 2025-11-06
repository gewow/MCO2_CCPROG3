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

public class Microchip{
    private int xPosition;
    private int yPosition;
    private boolean isCollected;
    private char symbol;

    /**
     * Constructs a new microchip at the specific coordinate.
     * 
     * post-condition: microchip is created at the given coordinates, isCollected set to false then symbol initialized as 'M'
     * @param xPosition represents x coordinate of microchip
     * @param yPosition represents y coordinate of microchip
     */
    public Microchip (int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.isCollected = false;
        this.symbol = 'M';
    }

    /**
     * Gets the x coordinate of the microchip.
     * 
     * @return xPosition
     */
    public int getXPosition(){
        return xPosition;
    }

    /**
     * Gets the y coordinate of the microchip
     * 
     * @return yPosition
     */
    public int getYPosition(){
        return yPosition;
    }

    /**
     * Gets the symbol representing the microchip.
     * 
     * @return symbol
     */
    public char getSymbol(){
        return symbol;
    }

    /**
     * Checks if the microchip has been collected.
     * 
     * @return isCollected (true if collected, false otherwise)
     */
    public boolean getIsCollected(){
        return isCollected;
    }

     /**
     * Sets the collected status of the microchip
     * 
     * @param isCollected (true if microchip has been collected, false otherwise)
     * 
     */
    public void setCollected(boolean isCollected){
        this.isCollected = isCollected;
    }
}
