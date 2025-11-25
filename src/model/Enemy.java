package model;

/**
 * 
 * Represents an enemy character in the game.
 * 
 * <p>
 * Enemies are a type (a subclass) of Characters that move automatically in an up-down or left-right direction.
 * Interacts with the player by causing death on impact.
 * </p>
 * 
 * @author Anton Luis Galido
 * @version 1.0
 * 
 * 
 */
public class Enemy extends Characters implements EffectOnPlayer{
    private char direction;

    /**
     * Constructs an enemy at the specified direction with an initial movement direction.
     * 
     * @param xPosition initial x coordinate.
     * @param yPosition initial y coordinate.
     * @param direction initial direction (either w, a, s, d).
     */
    public Enemy(int xPosition, int yPosition, char direction) {
        super(xPosition, yPosition);
        this.direction = direction;
    }

    /**
     * Moves the enemy in its current direction if the next position is valid.
     * 
     * <p>
     * If the movement is blocked, it just turns back.
     * </p>
     * 
     * @param direction the direction of the enemy.
     * @param map the map used to validate the movement.
     */
    @Override
    public void move(char direction, Maps map) {
        int nextX = this.xPosition;
        int nextY = this.yPosition;

        switch(this.direction) {
            case 'w':
                nextX--;
                break;
            case 'a':
                nextY--;
                break;
            case 's':
                nextX++;
                break;
            case 'd':
                nextY++;
                break;
        }

        if (canMoveTo(nextX, nextY, map)){
            this.xPosition = nextX;
            this.yPosition = nextY;
        }
        else {
            // reverses direction
            switch(this.direction) {
                case 'w': this.direction = 's'; 
                break;
                case 'a': this.direction = 'd'; 
                break;
                case 's': this.direction = 'w'; 
                break;
                case 'd': this.direction = 'a'; 
                break;
            }
        }
    }
    
    /**
     * Checks if the given position is valid to move to.
     * 
     * @param x the target x coordinate.
     * @param y the target y coordinate.
     * @param map the map being validated.
     * 
     * @return true if it's valid, false if not. 
     */
    @Override
    public boolean canMoveTo(int x, int y, Maps map) { 
        //use the map's isValidPosition method to check if the player is moving within the coordinates of the map
        if (!map.isValidPosition(x, y)){
            return false;
        }
        
        Tile tile = map.getTile(x, y);

        //safety check for null tiles
        if (tile == null){
            return false;
        }

        // if wall tile, move enemy to opposite direction
        if (tile.getSymbol() == '#') {
            return false;
        }

        return true;
    }


    /**
     * Handles what happens when the player enters the same tile as the enemy.
     * 
     * @param player represents the player itself.
     * @param map the map that both characters are on.
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
            player.killPlayer("caught by the enemy.");
    }

    /**
     * Gets the movement direction of the enemy.
     * 
     * @return direction char (w, a, s, d).
     */
    public char getDirection(){
        return direction;
    }

    /**
     * Returns the character representing the enemy's sprite based on the direction.
     * 
     * @return the sprite character for rendering.
     */
    public char getSpriteChar(){
        switch (direction){
            case 'w':
                return '↑';
            case 'a':
                return '←';
            case 's':
                return '↓';
            case 'd':
                return '→';
            default:
                return ':';
        }
    }
}