/**
 * Represents the Player of the game itself
 * 
 * <p>
 * The player has a starting position on the map itself. It also includes an inventory,
 * a status on whether they're still alive, a counter for the number of microchips collected,
 * and they can move, interact with items and reset as well.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */

public class Player {
    private int xPosition;
    private int yPosition;
    private Inventory inventory;
    private boolean isAlive;
    private int microchips;

    /**
     * Constructs a Player object at the given coordinates.
     * 
     * <p>
     * pre-condition: coordinates are valid
     * post-condition: Player is alive and with no microchips, and has an empty inventory
     * </p>
     * @param xPosition the x coordinate of the player
     * @param yPosition the y coordinate of the player
     * 
     */
    public Player(int xPosition, int yPosition){
       this.xPosition = xPosition;
       this.yPosition = yPosition;
       this.inventory = new Inventory();
       this.isAlive = true; 
       this.microchips = 0;
    }

    /**
     * Moves the player in the specified direction
     * 
     * <p>
     * pre-condition: the user input should be limited to the available controls, and map should be initialized
     * post-condition: Player's movement is updated if it's valid
     * </p>
     * @param direction the specific direction where the player could move
     * @param map the map being played on
     */
    public void move(char direction, Map map){
        int tempX = this.xPosition;
        int tempY = this.yPosition;
        
        switch (direction) {
            case 'w':
                tempX--;
                break;
            case 'a':
                tempY--;
                break;
            case 's':
                tempX++;
                break;
            case 'd':
                tempY++;
                break;
        }
        
        if (canMoveTo(tempX, tempY, map)){
            this.xPosition = tempX;
            this.yPosition = tempY;
            
            // After moving, trigger the tile's onPlayerEnter to handle chain reactions
            Tile tile = map.getTile(this.xPosition, this.yPosition);
            if (tile != null) {
                tile.onPlayerEnter(this, map); // Pass null for game since we don't need it in recursive calls
            }
        }
    }

    /**
     * Checks if the player is allowed to move at the specific direction.
     * 
     * <p>
     * pre-condition: x and y are within bounds
     * post-condition: player is allowed to move 
     * </p>
     * @param x x coordinate of the position to go to
     * @param y y coordinate of the position to go to
     * @param map the map being played on
     * @return true if allowed, false otherwise
     */
    public boolean canMoveTo(int x, int y, Map map){
        //use the map's isValidPosition method to check if the player is moving within the coordinates of the map
        if (!map.isValidPosition(x, y)){
            return false;
        }
        
        Tile tile = map.getTile(x, y);
        if (tile != null && tile.getSymbol() == '#'){
            return false;
        }

        Door door = map.getDoorAt(x, y);
        if (door != null && !door.canPlayerEnter(this)){
            return false;
        }

        return true;
    }

    /**
     * Collects boots and put to inventory.
     * 
     * <p>
     * pre-condition: boot is valid and NOT null
     * post-condition: boots is added to inventory and set as collected
     * </p>
     * @param boot the boots being collected
     */
    public void collectBoot(Boots boot){
        inventory.addBoots(boot);
        boot.setIsCollected(true);
    }

   /**
     * Collects keys and put to inventory.
     * 
     * <p>
     * pre-condition: Key is valid and NOT null
     * post-condition: Key is added to inventory and set as collected
     * </p>
     * @param key the key being collected
     */
    public void collectKey(Key key){
        inventory.addKey(key);
        key.setIsCollected(true);
    }

    /**
     * Collects microchips and put to inventory.
     * 
     * <p>
     * pre-condition: microchip is valid and NOT null
     * post-condition: microchip is incremented and added to inventory and set as collected
     * </p>
     * @param microchip the microchip being collected
     */
    public void collectMicrochip(Microchip microchip){
        this.microchips++;
        microchip.setCollected(true);
    }

    /**
     * Returns the number of microchips.
     * 
     * <p>
     * pre-condition: player is initialzied
     * post-condition: returns the number of microchips
     * </p>
     * @return the number of microchips 
     */
    public int getMicrochips(){
        return this.microchips;
    }

    /**
     * Eliminates the player
     * 
     * <p>
     * pre-condition: player is currently alive
     * post-condition: player is then killed with a print statement
     * </p>
     * @param reason the specific reason for the death
     */
    public void killPlayer(String reason){
        this.isAlive = false;
        System.out.println("You " + reason + "!");
    }

    /**
     * Player is reset to their starting position along with inventory and microchip count.
     * 
     * <p>
     * pre-condition: map is initialzied
     * post-condition: player is reset along with all other items and inventory
     * </p>
     * @param map the current map placed on after reset
     * @param startX the starting X position of the player on the map
     * @param startY the starting Y position of the player on the map
     */
    public void resetPlayer(Map map, int startX, int startY){
        setPosition(startX, startY);
        inventory.resetInventory();
        this.isAlive = true;
        this.microchips = 0;
    }


    /**
     * Checks status whether player is still alive or not.
     * 
     * @return true if yes, false otherwise.
     */
    public boolean isAlive(){
        return this.isAlive;
    }

    /**
     * Gets the x coordinate of the player.
     * 
     * @return the specific value of the x coordinate
     */
    public int getXPosition(){
        return this.xPosition;
    }

    /**
     * Gets the y coordinate of the player.
     * 
     * @return the specific value of the y coordinate
     */
    public int getYPosition(){
        return this.yPosition;
    }

    /**
     * Sets the position of the player based on the specified coordinates.
     * 
     * @param x x coordinate of player
     * @param y y coordinate of the player
     * 
     */
    public void setPosition(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }

    /**
     * Gets the player's inventory.
     * 
     * @return the player's inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }
}
