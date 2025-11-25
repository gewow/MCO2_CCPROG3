package model;

/**
 * Represents the wall tile in the game.
 * 
 * <p>
 * The wall tile being an obstacle where players and enemies can't enter.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class WallTile extends Tile{

    /**
     * Constructs a wall tile at the specified coordinates with a given symbol '#'.
     * 
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     * @param symbol the character representing the wall tile (used for mapping to image).
     */
    public WallTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    /**
     * Determines whether the player could enter the tile.
     * 
     * @param player the player attempting to enter
     * @return false since there's no condition where walls are enterable.
     * 
     */
    @Override
    public boolean canPlayerEnter(Player player){
        return false;
    }
}
