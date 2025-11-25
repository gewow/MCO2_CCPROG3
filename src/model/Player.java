package model;

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

public class Player extends Character{
    private Inventory inventory;
    private boolean isAlive;
    private int microchips;
    private char lastDirection;
    private char playerOrientation;

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
       super(xPosition, yPosition);
       this.inventory = new Inventory();
       this.isAlive = true; 
       this.microchips = 0;
       this.playerOrientation = 's'; //s for south since d is for door already
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
    @Override
    public void move(char direction, Maps map){
        int tempX = this.xPosition;
        int tempY = this.yPosition;
       
        
        switch (direction) {
            case 'w':
                tempX--;
                if ((tempX == map.getTile(tempX, tempY).getXPosition() && tempY == map.getTile(tempX, tempY).getYPosition()) && map.getTile(tempX, tempY).getSymbol() == 'W'){
                    this.playerOrientation = '5';
                }
                else{
                    this.playerOrientation = 'u';
                }
                break;
            case 'a':
                tempY--;
                if ((tempX == map.getTile(tempX, tempY).getXPosition() && tempY == map.getTile(tempX, tempY).getYPosition()) && map.getTile(tempX, tempY).getSymbol() == 'W'){
                    this.playerOrientation = '4';
                }
                else{
                    this.playerOrientation = 'l';
                }
                break;
            case 's':
                tempX++;
                if ((tempX == map.getTile(tempX, tempY).getXPosition() && tempY == map.getTile(tempX, tempY).getYPosition()) && map.getTile(tempX, tempY).getSymbol() == 'W'){
                    this.playerOrientation = '6';
                }
                else{
                    this.playerOrientation = 's';
                }
                break;
            case 'd':
                tempY++;
                if ((tempX == map.getTile(tempX, tempY).getXPosition() && tempY == map.getTile(tempX, tempY).getYPosition()) && map.getTile(tempX, tempY).getSymbol() == 'W'){
                    this.playerOrientation = '3';
                }
                else{
                    this.playerOrientation = 'r';
                }
                break;
        }

        
        if (canMoveTo(tempX, tempY, map)){
            this.xPosition = tempX;
            this.yPosition = tempY;
            
            // sets the direction of the player to lastDirection to determine the past movement (for ice tile mainly)
            lastDirection = direction;

            Item item = map.getItemAt(this.xPosition, this.yPosition);
            if (item != null && !item.getIsCollected()){
                this.collectItem(item);
            }

            // After moving, trigger the tile's onPlayerEnter to handle chain reactions
            Tile tile = map.getTile(this.xPosition, this.yPosition);
            if (tile != null) {
                tile.onPlayerEnter(this, map); // Pass null for game since we don't need it in recursive calls
            }

            Door door = map.getDoorAt(this.xPosition, this.yPosition);
            if (door != null){
                door.onPlayerEnter(this, map);
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
    @Override 
    public boolean canMoveTo(int x, int y, Maps map){
        //use the map's isValidPosition method to check if the player is moving within the coordinates of the map
        if (!map.isValidPosition(x, y)){
            return false;
        }
        
        Tile tile = map.getTile(x, y);

        //safety check for null tiles
        if (tile == null){
            return false;
        }

        if (!tile.canPlayerEnter(this)){
            return false;
        }

        Door door = map.getDoorAt(x, y);
        if (door != null && !door.canPlayerEnter(this)){
            return false;
        }

        return true;
    }

    /**
     * returns the last direction that the player did
     * 
     * @return the last direction value in a char type 
     */
    public char getLastDirection() {
        return lastDirection;
    }

    /**
     * Collects item and put to inventory.
     * 
     * <p>
     * pre-condition: item is valid and NOT null
     * post-condition: item is added to inventory and set as collected
     * </p>
     * @param boot the boots being collected
     */
    public void collectItem(Item item){
        if (item != null && !item.getIsCollected()){
            item.onPlayerPickup(this);
        }
    }


    public void addMicrochips(){
        this.microchips++;
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
        if (reason == "drowned"){
            playerOrientation = '1';
        }
        else if (reason == "caught on fire"){
            playerOrientation = '2';
        }
        this.isAlive = false;
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
    public void resetPlayer(Maps map, int startX, int startY){
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

    public char getPlayerOrientation(){
        return playerOrientation;
    }
}
