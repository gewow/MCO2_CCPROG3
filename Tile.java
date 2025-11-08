/**
 * This represents the tiles and its different types seen in the game map.
 * 
 * <p>
 * Tiles have different types and different characteristics depending on the game state. 
 * The player may or may not be allowed to step depending on if they hold the required inventory item. 
 * </p>
 * @author Anton Galido
 * @version 1.0
 * 
 */

public abstract class Tile {
    private int xPosition;
    private int yPosition;
    private char symbol;
    
    /**
     * 
     * Constructs a Tile object with a specified position with a unique symbol depending on the type, and the specified direction/coordinates.
     * 
     * 
     * <p>
     * pre-condition: x and y coordinates are within bounds of the map
     * post-condition: a new Tile is created
     * </p>
     * @param xPosition the x coordinate of that tile
     * @param yPosition the y coordinate of that tile
     * @param symbol the specific symbol of the specific Tile
     * @param direction the specific direction
     */
    public Tile (int xPosition, int yPosition, char symbol) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.symbol = symbol;
    }
    
    /**
     * Checks if player can enter a specific tile.
     * 
     * <p>
     * pre-condition: player must not be null
     * post-condition: returns true if the tile is walkable via the player
     * </p>
     * @param player the current Player
     * @return true if allowed, false if otherwise
     */
    public abstract boolean canPlayerEnter(Player player);
    
    /**
     * Handles the actions when players enter the tile.
     * 
     * <p>
     * pre-condition: player and map is NOT null
     * post-condition: executes the result depending on the specific tile
     * </p>
     * @param player the player itself
     * @param map the map being played on
     */
    public abstract void onPlayerEnter(Player player, Map map);

    /**
     * Gets the x coordinate of the Tile.
     * 
     * @return x-coordinate
     * 
     */
    public int getXPosition() {
        return xPosition;
    }
    
    /**
     * Gets the y coordinate of the Tile.
     * 
     * @return y-coordinate
     * 
     */
    public int getYPosition() {
        return yPosition;
    }
    
    
    /**
     * Gets the symbol of the specific tile.
     * @return character symbol of a specific tile
     */
    public char getSymbol() {
        return symbol;
    }
}
