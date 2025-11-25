package model;

/**
 * Represents the base floor tile in the game.
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class FloorTile extends Tile{

    /**
     * Constructs the floor tile at specified coordinates and a . symbol.
     * 
     * @param xPosition x coordinate.
     * @param yPosition y coordinate.
     * @param symbol the char representing the floor tile (used for mapping to image).
     */
    public FloorTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }


    /**
     * Determines whether player can enter the tile or not.
     * 
     * @param player the player themselves.
     * @return true if yes, false if not.
     */
    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }
}
