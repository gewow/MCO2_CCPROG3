/**
 * This represents the levels of the game, containing the visual layout of the map per level and all the other interactive elements.
 * Each level having different layouts as well as the placement and num of microchips, and player start/exit positions.
 * 
 * @author Melangelo Guanzon 
 * @author Anton Galido
 * @version 1.0
 */

public class Level {
    private int levelNumber;
    private Map map;
    private char[][] layout;
    private int requiredChips;
    private int playerStartX;
    private int playerStartY;
    private int exitX;
    private int exitY;
    private int width;
    private int height;
    private boolean isCompleted;

    private static final char[][] LEVEL_1_LAYOUT ={
    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}, 
    {'#','.','E','.','.','.','#','F','F','F','F','F','F','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','d','F','F','F','k','F','F','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','F','F','F','F','F','F','#','.','.','M','.','.','#'},
    {'#','#','#','#','#','#','#','F','F','F','F','F','#','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','#','#','d','#','#','#','#','.','B','.','.','.','#'},
    {'#','.','.','.','.','.','#','.','.','.','.','.','.','#','.','.','.','.','.','#'},
    {'#','.','.','k','.','.','d','.','.','.','.','.','.','D','.','.','.','.','.','#'},
    {'#','M','.','.','.','.','#','.','k','.','.','K','.','#','.','.','M','.','.','#'},
    {'#','#','#','#','#','#','#','.','.','.','.','.','.','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','.','.','.','S','.','.','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','.','.','.','.','.','.','#','.','.','.','.','.','#'},
    {'#','M','.','.','.','.','d','.','M','.','.','M','.','#','#','#','#','#','#','#'},
    {'#','.','.','.','.','.','#','.','.','.','.','.','.','#','W','W','W','W','W','#'},
    {'#','.','.','.','.','.','#','#','#','#','D','#','#','#','W','W','W','W','W','#'},
    {'#','.','P','.','.','.','.','#','W','W','W','W','W','W','W','W','W','W','W','#'},
    {'#','.','.','.','.','.','.','#','W','W','W','W','W','W','W','W','W','W','W','#'},
    {'#','.','K','.','.','M','.','#','W','W','W','W','k','W','W','W','W','W','W','#'},
    {'#','.','.','.','.','.','.','#','W','W','W','W','W','W','W','W','W','W','W','#'},
    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}}; 

    private static final char[][] LEVEL_2_LAYOUT = {
    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}, 
    {'#','S','.','.','M','.','.','.','.','#','K','.','.','.','.','M','.','.','.','#'},
    {'#','.','#','#','#','#','#','.','#','#','#','#','#','.','#','#','#','#','.','#'},
    {'#','M','>','>','>','>','>','P','#','W','W','W','#','.','#','F','F','F','.','#'},
    {'#','.','#','#','#','#','#','K','#','W','k','W','#','.','#','F','M','F','.','#'},
    {'#','.','.','.','.','.','.','.','.','W','W','W','D','.','.','>','>','>','.','#'},
    {'#','#','#','D','#','#','#','#','#','#','#','#','#','.','#','#','#','#','.','#'},
    {'#','.','^','^','^','M','^','^','^','.','.','.','.','.','.','.','.','.','M','#'},
    {'#','.','.','#','#','#','#','#','.','.','#','#','#','#','#','#','#','.','#','#'},
    {'#','K','.','>','>','M','>','>','.','#','#','W','W','W','W','W','#','.','B','#'},
    {'#','.','#','#','#','#','#','.','.','#','W','W','W','W','W','W','W','d','#','#'},
    {'#','.','^','^','^','^','^','.','#','#','W','W','W','W','W','W','#','.','#','#'},
    {'#','.','#','#','#','#','#','.','#','F','F','F','F','F','F','F','#','.','k','#'},
    {'#','.','.','.','.','.','.','.','.','F','F','F','F','F','F','F','#','.','#','#'},
    {'#','#','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','M','#'},
    {'#','<','<','<','<','<','<','<','.','.','.','.','.','.','.','.','.','.','#','#'},
    {'#','#','#','#','#','#','#','#','.','.','v','v','v','v','v','v','.','#','#','#'},
    {'#','M','.','.','.','.','.','.','.','#','#','#','#','#','#','#','d','.','.','#'},
    {'#','.','#','#','#','#','#','#','.','#','E','.','.','.','.','.','.','.','.','#'},
    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};

    private static final int LEVEL_1_REQUIRED_CHIPS = 7; //Player can exit once player gets 3 microchips
    private static final int LEVEL_2_REQUIRED_CHIPS = 9; //Player can exit once player gets 5 microchips

    /**
     * Constructs a level based on the level num.
     * 
     * Initializes the layout, required chips, and the actual map associated.
     * 
     * <p>
     * pre-condition: levelNumber must be 1 or 2
     * post-condition: Level object is created with map initialized
     * </p>
     * @param levelNumber The level num to be initialized (either 1 or 2 only).
     */
    public Level (int levelNumber){
        this.levelNumber = levelNumber;
        this.isCompleted = false;

        switch(this.levelNumber){
            case 1:
                this.layout = LEVEL_1_LAYOUT;
                this.requiredChips = LEVEL_1_REQUIRED_CHIPS;
                break;
            case 2:
                this.layout = LEVEL_2_LAYOUT;
                this.requiredChips = LEVEL_2_REQUIRED_CHIPS;
                break;
        }

        this.height = layout.length; 
        this.width = layout[0].length; 
        createMap();
    }


    /**
     * This initializes and fills the map with the appropriate and corresponding elements.
     * 
     * <p>
     * pre-condition: layout array must be initialized
     * post-condition: map is created with tiles, doors, keys, boots, and microchips
     * </p>
     */
    public void createMap(){
        Tile[][] tiles = new Tile[this.height][this.width];
        Door[][] doors = new Door[this.height][this.width];
        Key[][] keys = new Key[this.height][this.width];
        Boots[][] boots = new Boots[this.height][this.width];
        Microchip[][] microchips = new Microchip[this.height][this.width];

        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                if (layout[x][y] == '.' || layout[x][y] == 'W' || layout[x][y] == 'F' || 
                    layout[x][y] == '^' || layout[x][y] == 'v' || layout[x][y] == '<' || 
                    layout[x][y] == '>' || layout[x][y] == '#' || layout[x][y] == 'S' || layout[x][y] == 'E'){
                    placeTiles(x, y, layout[x][y], tiles);
                }
                else if (layout[x][y] == 'D' || layout[x][y] == 'd'){
                    placeDoors(x, y, layout[x][y], doors);
                }
                else if (layout[x][y] == 'K' || layout[x][y] == 'k' || 
                         layout[x][y] == 'B' || layout[x][y] == 'P' ||
                         layout[x][y] == 'M'){
                            placeItems(x, y, layout[x][y], keys, boots, microchips);
                }
            }
        }

        this.map = new Map(tiles, doors, keys, boots, microchips, this.width, this.height);
    }

    /**
     * Converts the inventory item symbols/characters to their respective game objects.
     * 
     * pre-condition: keys, boots, and microchips are initialized
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param symbol Symbol representing the inventory item
     * @param keys 2D array of keys
     * @param boots 2D array of boots
     * @param microchips 2D array of microchips
     */
    public void placeItems(int x, int y, char symbol, Key[][] keys, Boots[][] boots, Microchip[][] microchips){
        switch(symbol){
            case 'K': //blue key
                keys[x][y] = new Key(x, y, "blue", 'K');
                break;
            case 'k': //red key
                keys[x][y] = new Key(x, y, "red", 'k');
                break;
            case 'B': //fireboots
                boots[x][y] = new Boots(x, y, "fireboots", 'B');
                break;
            case 'P': //flippers
                boots[x][y] = new Boots(x, y, "flippers", 'P');
                break;
            case 'M': //microchips
                microchips[x][y] = new Microchip(x, y);
                break;
        }

    }

    /**
     * Converts the type of doors and their symbols/characters to their respective game objects.
     * 
     * pre-condition: doors array is initialized
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param symbol Symbol representing the inventory item
     * @param doors 2D array of doors
     */
    public void placeDoors(int x, int y, char symbol, Door[][] doors){
        switch(symbol){
            case 'D': //blue door
                doors[x][y] = new Door(x, y, "blue", symbol);
                break;
            case 'd': //red door
                doors[x][y] = new Door(x, y, "red", symbol);
                break;
        }
    }

    /**
     * Converts the different type of tiles  and their symbols/characters to their respective game objects.
     * 
     * <p>
     * pre-condition: tiles array is initialized
     * post-condition: specific tiles are placed in the map
     * </p>
     * @param x The x coordinate
     * @param y The y coordinate
     * @param symbol Symbol representing the inventory item
     * @param tiles 2D array of tiles
     */
    public void placeTiles(int x, int y, char symbol, Tile[][] tiles){
        switch(symbol){
            case 'S': //starting tile
                tiles[x][y] = new Tile(x, y, '.', null);
                this.playerStartX = x;
                this.playerStartY = y;
                break;
            case 'E': //exit tile
                tiles[x][y] = new Tile(x, y, symbol, null);
                this.exitX = x;
                this.exitY = y;
                break;
            case '.': //empty tile
                tiles[x][y] = new Tile(x, y, symbol, null);
                break;
            case 'W': //water tile
                tiles[x][y] = new Tile(x, y, symbol, null);
                break;
            case 'F': //fire tile
                tiles[x][y] = new Tile(x, y, symbol, null);
                break;
            case '#': // wall tile
                tiles[x][y] = new Tile(x, y, symbol, null);
                break;
            case '^': //force floor tile UP
                tiles[x][y] = new Tile(x, y, symbol, "Up");
                break;
            case 'v': //force floor tile DOWN
                tiles[x][y] = new Tile(x, y, symbol, "Down");
                break;
            case '<': //force floor tile LEFT
                tiles[x][y] = new Tile(x, y, symbol, "Left");
                break;
            case '>': //force floor tile RIGHT
                tiles[x][y] = new Tile(x, y, symbol, "Right");
                break;
        }
    }

    /**
     * Returns the player starting x position.
     * 
     * @return the player's start x position
     */
    public int getPlayerStartX(){
        return playerStartX;
    }

    /**
     * Returns the player starting y position.
     * 
     * @return the player's start y position
     */
    public int getPlayerStartY(){
        return playerStartY;
    }

    /**
     * Returns the number of required microchips for finishing the level.
     * 
     * @return required microchips
     */
    public int getRequiredChips(){
        return requiredChips;
    }
    /**
     * Returns the map associated with the specific level. 
     * 
     * @return the map 
     */
    public Map getMap(){
        return map;
    }
    /**
     * Returns the level number.
     * @return level number
     */
    public int getLevelNumber(){
        return levelNumber;
    }
    /**
     * Returns the boolean status of isCompleted.
     * 
     * @return true if level is complete and false if not
     */
    public boolean isCompleted(){
        return isCompleted;
    }
    /**
     * Checks whether the player completed the level.
     * It's completed if all the microchips are collected and reaches exit tile.
     * 
     * <p>
     * pre-condition: player object must be initialized
     * post-condition: isCompleted is updated if player meets the completion criteria
     * </p>
     * 
     * @param player object to check
     * @return True if completed, false otherwise
     */
    public boolean checkCompletion(Player player){
        //Player must collect all microchips and player must be on exit tile
        if (player.getMicrochips() == this.requiredChips && (player.getXPosition() == this.exitX && player.getYPosition() == this.exitY)){
            isCompleted = true;
            return true;
        }
        return false;
    
    }

    /**
     * Resets the level by marking it as not complete then regenerating the map
     */
    public void reset(){
        this.isCompleted = false;
        createMap();
    }
}
