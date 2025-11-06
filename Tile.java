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

public class Tile {
    private int xPosition;
    private int yPosition;
    private char symbol;
    private String direction;
    
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
    public Tile (int xPosition, int yPosition, char symbol, String direction) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.symbol = symbol;
        this.direction = direction;
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
    public boolean canPlayerEnter(Player player) {

        switch(symbol) {
            case '.': //blank tile
                return true;
            case 'W': //water tile
                return player.getInventory().hasBoots("flippers");
            case '#': //wall tile
                return false;
            case 'F': //fire tile
                return player.getInventory().hasBoots("fireboots");
            case 'E': //exit tile
                return true;
            case '>': // right
            case '^': // up
            case 'v': // down
            case '<': // left
                return true;
            default:
                return false;
        }
    }
    
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
    public void onPlayerEnter(Player player, Map map) {
        switch (symbol) {
            case 'W':
                if (!player.getInventory().hasBoots("flippers")) {
                    player.killPlayer("drowned");
                }
                break;
            case 'F':
                if (!player.getInventory().hasBoots("fireboots")) {
                    player.killPlayer("caught on fire");
                }
                break;
            case '^':
                player.move('w', map);
                break;
            case '<':
                player.move('a', map);
                break;
            case 'v':
                player.move('s', map);
                break;
            case '>':
                player.move('d', map);
                break;
        }
    }
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
     * Returns the direction of the tile (for the force floor type).
     * 
     * @return direction of the force floor tile
     */
    public String getDirection() {
        return direction;
    }
    
    /**
     * Gets the symbol of the specific tile.
     * @return character symbol of a specific tile
     */
    public char getSymbol() {
        return symbol;
    }
}
