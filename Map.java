
/**
 * This represents the game map containing the tiles, doors, and inventory items to be picked up.
 * The map is defined with a 2D grid with each object stored in a corresponding 2D array.
 * 
 * @author Melangelo Guanzon
 * @author Anton Galido
 * @version 1.0
 */
public class Map {
    private Tile[][] grid;
    private Door[][] doors;
    private Key[][] keys;
    private Boots[][] boots;
    private Microchip[][] microchips;
    private int width;
    private int height;
    
    
    /**
     * Constructs the map object that holds all the inventory items as well as the doors and tiles within a level.
     * 
     * @param grid The 2D array representing the layout of the map
     * @param doors 2D array representing the containing doors in the map
     * @param keys 2D array representing the containing keys in the map
     * @param boots 2D array representing the containing boots in the map
     * @param microchips 2D array representing the containing microchip/s in the map
     * @param width map width
     * @param height map height
     * 
     */
    public Map(Tile[][] grid, Door[][] doors, Key[][] keys, Boots[][] boots, Microchip[][] microchips, int width, int height) {
        this.grid = grid;
        this.doors = doors;
        this.keys = keys;
        this.boots = boots;
        this.microchips = microchips;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Returns the tile at the specified position.
     * 
     * <p>
     * pre-condition: x and y coordinates are within the map bounds
     * post-condition: returns the Tile object at the given coordinates without modifying the map
     * </p>
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @return the grid or tile object at the specified coordinates
     */
    public Tile getTile(int x, int y) {
        return this.grid[x][y];
    }
    
    /** 
     * Sets a tile at the specified position.
     * 
     * <p>
     * pre-condition: both coordinates are within bound and tile is NOT null
     * post-condition: the specified grid position is updated with the new tile
     * </p>
     * @param x The x coordinate
     * @param y The y coordinate
     * @param tile the tile object to be set
     */
    public void setTile(int x, int y, Tile tile) {
        this.grid[x][y] = tile;
    }

    /** 
     * Returns the map width.
     * 
     * @return width value
     */
    public int getWidth(){
        return this.width;
    }
    /** 
     * Returns the map height.
     * @return height value
     */
    public int getHeight(){
        return this.height;
    }
    
    /**
     * Returns a specific door.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * 
     * @return the specified door at the specific coordinates
     */
    public Door getDoorAt(int x, int y) {
        return this.doors[x][y];
    }

    /**
     * Returns the key at the specified coordinates.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return the keys at the given position
     */
    public Key getKeyAt(int x, int y) {
        return this.keys[x][y];
    }

    /**
     * Returns the boots at the specified coordinates.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return the boots at the given position
     */
    public Boots getBootsAt(int x, int y) {
        return this.boots[x][y];
    }

    /**
     * Returns the microchip/s at the specified coordinates.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * 
     * @return the microchips at the given position
     */
    public Microchip getMicrochipsAt(int x, int y) {
        return this.microchips[x][y];
    }

    /**
     * Returns the 2D array of all the doors at the map.
     * 
     * @return the 2D array of the door objects
     */
    public Door[][] getDoors() {
        return this.doors;
    }

    /**
     * Returns the 2D array of all the keys at the map.
     * 
     * @return the 2D array of all key objects
     */
    public Key[][] getKeys() {
        return this.keys;
    }

    /**
     * Returns the 2D array of all the boots at the map.
     * 
     * @return the 2D array of all boots objects
     */
    public Boots[][] getBoots() {
        return this.boots;
    }

    /**
     * Returns the 2D array of all the microchips at the map.
     * 
     * @return the 2D array of microchip objects
     */
     public Microchip[][] getMicrochips() {
        return this.microchips;
    }

    /**
     * Removes key in the map by collecting it, then replace it with a blank tile.
     * 
     * <p>
     * pre-condition: key exist on the map and is NOT null
     * post-condition: key should be marked as collected, then is replaced with blank tile
     * </p>
     * @param key The key object to be removed
     */
    public void removeKey(Key key) {

        int x = key.getXPosition();
        int y = key.getYPosition();

        if (keys[x][y] == key) {
            keys[x][y].setIsCollected(true);
            this.grid[x][y] = new Tile(x, y, '.', null);
        }
    }
     /**
     * Removes boots in the map by collecting it, then replace it with a blank tile.
     * 
     * <p>
     * pre-condition: boots exist on the map and is NOT null
     * post-condition: boots should be marked as collected, then is replaced with blank tile
     * </p>
     * @param boots The boots object to be removed
     */
    public void removeBoots(Boots boots) {
        int x = boots.getXPosition();
        int y = boots.getYPosition();

        if (this.boots[x][y] == boots) {
            this.boots[x][y].setIsCollected(true);
            this.grid[x][y] = new Tile(x, y, '.', null);
        }
    }

    /**
     * Removes microchip in the map by collecting it, then replace it with a blank tile.
     * 
     * <p>
     * pre-condition: microchips exist on the map and is NOT null
     * post-condition: microchips should be marked as collected, then is replaced with blank tile
     * </p>
     * @param microchip The microchip object to be removed
     */
    public void removeMicrochips(Microchip microchip) {
        int x = microchip.getXPosition();
        int y = microchip.getYPosition();

        if (this.microchips[x][y] == microchip) {
            this.microchips[x][y].setCollected(true);
            this.grid[x][y] = new Tile(x, y, '.', null);
        }
    }

    /**
     * Returns true or false if the given coordinates is a valid position.
     * 
     * <p>
     * pre-condition: x and y should be integers
     * post-condition: returns boolean (true or false) if it's in a valid position (in bounds)
     * </p>
     * @param x The x coordinate
     * @param y The y coordinate
     * 
     * @return true if the position is valid (in bounds), or false if not
     */
    public boolean isValidPosition(int x, int y) {
        if (x >= height || y >= width) { 
            return false;
        }
        else if (x < 0 || y < 0) {
            return false;
        }
        return true;
    }
    
    
    /** 
     * Returns the position of the Exit tile.
     * 
     * @return the tile object representing the exit, null if it's not found
     */
    public Tile findExitPosition() {
        int x, y;

        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                if (grid[x][y] != null && grid[x][y].getSymbol() == 'E') {
                    return grid[x][y];
                }
            }
        }
        return null;
    }

}
