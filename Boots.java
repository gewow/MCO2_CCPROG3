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

public class Boots{
    private int xPosition;
    private int yPosition;
    private boolean isCollected;
    private String type;
    private char symbol;

    /**
     * Constructs a Boots object with a specified x and y position, type of boots, and its symbol.
     * 
     * @param xPosition the x coordinate of the Boots
     * @param yPosition the y coordinate of the Boots
     * @param type the type of boots
     * @param symbol the symbol representing with each type of boots
     */
    public Boots(int xPosition, int yPosition, String type, char symbol){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.type = type;
        this.isCollected = false;
        this.symbol = symbol;
    }

    /**
     * Returns the x coordinate of the boots in the map.
     * 
     * <p>
     * pre-condition: The boots must be instantiated
     * post-condition: Returns the x coordinate/position of the boots 
     * </p>
     * @return x-coordinate of the boots
     */
    public int getXPosition(){
        return xPosition;
    }

    /**
     * Returns the y coordinate of the boots in the map.
     * <p>
     * pre-condition: The boots must be instantiated
     * post-condition: Returns the y coordinate/position of the boots 
     * </p>
     * @return y-coordinate of the boots
     */
    public int getYPosition(){
        return yPosition;
    }

    /**
     * Returns the symbol that represents the boots in the map.
     * 
     * <p>
     * pre-condition: Boots should be instantiated
     * post-condition: returns the char symbol of the boots
     * </p>
     * @return character symbol of boots
     */
    public char getSymbol(){
        return symbol;
    }

    /**
     * Returns true if the boots is collected and false if not, based on isCollected.
     * 
     * <p>
     * pre-condition: Boots should be instantiated
     * post-condition: true if the boots is collected, false otherwise
     * </p>
     * @return true if collected, false otherwise.
     */
    public boolean getIsCollected(){
        return isCollected;
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

    /**
     * Sets and updates the status of the collected boots.
     * 
     * post-condition: updates the status of the collected boots.
     * @param isCollected sets to true if collected and false if otherwise
     */
    public void setIsCollected(boolean isCollected){
        this.isCollected = isCollected;
    }
}