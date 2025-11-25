package model;

/**
 * Represents the Teleport tile in the game.
 * 
 * <p>
 * When a player steps on it without any teleport boots, they will be sent back to the spawn point in the map
 * </p>
 * 
 * @author Anton Luis Galido
 * @version 1.0
 * 
 */
public class TeleportTile extends Tile implements EffectOnPlayer{

    /**
     * Constructs a Teleport Tile at the specified coordinates with a given symbol.
     * @param xPosition x coordinate
     * @param yPosition y coordinate
     * @param symbol the char representing the teleport tile (used for mapping to image).
     */
    public TeleportTile(int xPosition, int yPosition, char symbol) {
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
     * Applies the teleport effect when the player enters the tile.
     * 
     * <p>
     * If the player doesn't have the teleport boots, they'll be sent back
     * to their spawn point.
     * </p>
     * 
     * @param player the player entering the tile
     * @param map the map where the tile's located.
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("teleportboots")) {
        // Send back to start
        player.setPosition(map.getPlayerStartX(), map.getPlayerStartY());
        }
    }


}