package model;

/**
 * Represents the fire tile in the game.
 * 
 * <p>
 * Causes the player to die from it unless wearing the fireboots.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class FireTile extends Tile implements EffectOnPlayer{

    /**
     * 
     * Constructs a fire tile at the specified coordinates and a given symbol.
     * 
     * @param xPosition x coordinate.
     * @param yPosition y coordinate.
     * @param symbol the char representing the fire tile (used for mapping to image).
     */
    public FireTile(int xPosition, int yPosition, char symbol){
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

    /**
     * Applies the effect when the player does enter.
     * 
     * <p>
     * If player is not wearing the boots, they will die.
     * </p>
     * 
     * @param player represents the player.
     * @param map represents the map where the tile is.
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("fireboots")){
            player.killPlayer("caught on fire");
        }
    }
}