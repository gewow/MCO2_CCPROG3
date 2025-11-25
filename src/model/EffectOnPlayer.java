package model;
/**
 * Represents the effect that occurs when the player enters a tile or interacts with something.
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */

public interface EffectOnPlayer {

    /**
     * Applies the effect when the player does enter.
     * 
     * @param player represents the player.
     * @param map the current map the player is in.
     */
    public void onPlayerEnter(Player player, Maps map);
}
