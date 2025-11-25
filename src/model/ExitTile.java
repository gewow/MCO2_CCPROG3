package model;

/**
 * Represents the tile where the player enters to exit the level (A sub class of Tile).
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class ExitTile extends Tile{

    /**
     * Constructs an Exit tile with a specific coordinates and a dedicated symbol. 
     * @param xPosition x coordinate of the exit tile
     * @param yPosition y coordinate of the exit tile
     * @param symbol represents the symbol for it (which is then connected to the image).
     */
    public ExitTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    /**
     * Determines whether the player can enter it yet.
     * 
     * @param player represents the player.
     * @return true if allowed to exit, false if not.
     */
    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }
}
