package model;

/**
 * Represents the Water tile in the game.
 * 
 * <p>
 * Only walkable when player has the flippers in their inventory. Otherwise, they will die.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class WaterTile extends Tile implements EffectOnPlayer{

    /**
     * Constructor for the Water tile at the specified coordinates with a given symbol 'W'.
     * @param xPosition x coordinate of the tile
     * @param yPosition y coordinate of the tile
     * @param symbol the character symbol to represent the tile (used for mapping to image).
     */
    public WaterTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

     /**
     * Determines whether the player could enter the tile.
     * 
     * @param player the player attempting to enter
     * @return true 
     * 
     */
    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    /**
     * Applies the effect when the player enters the water.
     * 
     * <p>
     * If they do not have flippers in their inventory, they will die.
     * </p>
     * 
     * @param player the player to enter the tile
     * @param map the map containing the tile
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("flippers")){
            player.killPlayer("drowned");
        }
    }
}
