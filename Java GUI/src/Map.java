
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
    private Item[][] items;
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
    public Map(Tile[][] grid, Door[][] doors, Item[][] items, int width, int height) {
        this.grid = grid;
        this.doors = doors;
        this.items = items;
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

    // for GUI to access all tiles to connect default ui to GUI
    public Tile[][] getGrid() {
        return this.grid;
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
     * Returns a specific item.
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * 
     * @return the specified item at the specific coordinates
     */
    public Item getItemAt(int x, int y){
        return this.items[x][y];
    }

    /**
     * Returns the 2D array of all the doors at the map.
     * 
     * @return the 2D array of the door objects
     */
    public Door[][] getDoors() {
        return this.doors;
    }

    

    public void removeItem(Item item){
        int x = item.getXPosition();
        int y = item.getYPosition();

        if(items[x][y] == item){
            items[x][y].setIsCollected(true);
            this.grid[x][y] = new FloorTile(x, y, '.');
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
