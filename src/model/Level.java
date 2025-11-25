package model;
import java.util.ArrayList;
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
    private Maps map;
    private char[][] layout;
    private int requiredChips;
    private int playerStartX;
    private int playerStartY;
    private int exitX;
    private int exitY;
    private int width;
    private int height;
    private boolean isCompleted;
    private ArrayList<Enemy> enemies;

    private static final char[][] LEVEL_1_LAYOUT ={
    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}, 
    {'#','.','E','.','.','.','#','F','F','F','F','F','F','#','M','.','B','.','.','#'},
    {'#','.','.','.','.','.','d','F','F','F','k','F','F','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','F','F','F','F','F','F','#','#','#','I','#','#','#'},
    {'#','#','#','#','#','#','#','F','F','F','F','F','#','#','#',';','.','.','#','#'},
    {'#','.','.','.','.','.','#','#','#','d','#','#','#','#','#','#','I','#','#','#'},
    {'#','.','.','I','.','.','#','.','.','.','.','.','.','#','I','I','I','I','I','#'},
    {'#','O','O','k','I','I','d','.','.','.','.','.','.','D','.','.','.','.','.','#'},
    {'#','M','O','I','.','.','#','.','k','.','.','K','.','#','.','.','M','.','.','#'},
    {'#','#','#','#','#','#','#','.','.','I','I','I','.','#','.','.','.','.','.','#'},
    {'#','.','.','.',':','.','#','.','.','I','S','I','.','#','.','.','.','.','.','#'},
    {'#','.','.','.','.','.','#','.','.','I','I','I','.','#','.','.','.','.','.','#'},
    {'#','M','.','T','.','I','d','.','M','.','.','M','.','#','#','#','#','#','#','#'},
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

    private static final int LEVEL_1_REQUIRED_CHIPS = 7; 
    private static final int LEVEL_2_REQUIRED_CHIPS = 9; 

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
        Item[][] items = new Item[this.height][this.width];
        enemies = new ArrayList<Enemy>();

        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                char symbol = layout[x][y];
                switch(symbol){
                    case '#': 
                        tiles[x][y] = new WallTile(x, y, symbol); //wall tile
                        break;
                    case '.':
                        tiles[x][y] = new FloorTile(x, y, symbol); //floor tile
                        break;
                    case 'F':
                        tiles[x][y] = new FireTile(x, y, symbol); //fire tile
                        break;
                    case 'W':
                        tiles[x][y] = new WaterTile(x, y, symbol); //water tile
                        break;
                    case 'I':
                        tiles[x][y] = new IceTile(x, y, symbol); //ice tile
                        break;
                    case 'O':
                        tiles[x][y] = new TeleportTile(x, y, symbol); //teleporter tile
                        break;
                    case 'E':
                        this.exitX = x;
                        this.exitY = y;
                        tiles[x][y] = new ExitTile(x, y, symbol); //exit tile
                        break;
                    case '>':
                        tiles[x][y] = new ForceFloor(x, y, symbol, "Right"); //Force floor - right
                        break;
                    case '<':
                        tiles[x][y] = new ForceFloor(x, y, symbol, "Left"); //Force floor - left
                        break;
                    case '^':
                        tiles[x][y] = new ForceFloor(x, y, symbol, "Up"); //Force floor - up
                        break;
                    case 'v':
                        tiles[x][y] = new ForceFloor(x, y, symbol, "Down"); //Force floor - down
                        break;
                    case 'd':
                        doors[x][y] = new Door(x, y, "red", symbol);
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'D':
                        doors[x][y] = new Door(x, y, "blue", symbol);
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'k':
                        items[x][y] = new Key(x, y, symbol, "red");
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'K':
                        items[x][y] = new Key(x, y, symbol, "blue");
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'B':
                        items[x][y] = new Boots(x, y, symbol, "fireboots");
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'P':
                        items[x][y] = new Boots(x, y, symbol, "flippers");
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'T':
                        items[x][y] = new Boots(x, y, symbol, "teleportboots");
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'M':
                        items[x][y] = new Microchip(x, y,symbol);
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case 'S':
                        this.playerStartX = x;
                        this.playerStartY = y;
                        tiles[x][y] = new FloorTile(x, y, '.');
                        break;
                    case ':': // enemy up and down
                        enemies.add(new Enemy(x, y, 's')); // s starts with down then goes up
                        tiles[x][y] = new FloorTile(x, y, '.'); 
                        break;
                    case ';': // enemy left and right
                        enemies.add(new Enemy(x, y, 'd')); // s starts with down then goes up
                        tiles[x][y] = new FloorTile(x, y, '.'); 

                }
            }
        }

        this.map = new Maps(tiles, doors, items, this.width, this.height);
        this.map.setStartPosition(playerStartX, playerStartY);
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
    public Maps getMap(){
        return map;
    }
    /**
     * Returns the level number.
     * @return level number
     */
    public int getLevelNumber(){
        return levelNumber;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
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
