package model;

/**
 * Represents the Ice tile in the game.
 * 
 * 
 * <p>
 * Pre-condition: player is initialized and map is valid.
 * Post-condition: player is moved one tile in the direction they last moved from.
 * </p>
 * 
 * @author Anton Luis Galido
 * @version 1.0
 */
public class IceTile extends Tile implements EffectOnPlayer{

    /**
     * 
     * Constructs an Ice tile at the specified coordinates and a given symbol.
     * 
     * @param xPosition x coordinate.
     * @param yPosition y coordinate.
     * @param symbol the char representing the Ice tile (used for mapping to image).
     */
    public IceTile(int xPosition, int yPosition, char symbol){
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
     * They get pushed to whatever direction they initially moved from.
     * </p>
     * 
     * @param player represents the player.
     * @param map represents the map where the tile is.
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
        char direction = player.getLastDirection();

        if (direction == 'w' || direction == 'a' || direction == 's' || direction == 'd') {
            player.move(direction, map);
        }
    }
}
