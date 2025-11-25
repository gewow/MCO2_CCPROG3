package model;

/**
 * Represents the force floor tile ingame.
 * 
 * <p>
 * when the player steps on a force floor tile, they get pushed depending on the shown direction of the tile.
 * </p>
 * 
 * @author Anton Luis Galido
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class ForceFloor extends Tile implements EffectOnPlayer{
    private String direction;

    /**
     * Constructs a force floor tile at the specified coordinates with a symbol and a push direction.
     * 
     * @param xPosition x coordinate of the tile.
     * @param yPosition y coordinate of the tile.
     * @param symbol the char representing the force floor tile (used for mapping to image).
     * @param direction the direction the player will be pushed to.
     */
    public ForceFloor(int xPosition, int yPosition, char symbol, String direction){
        super(xPosition, yPosition, symbol);
        this.direction = direction;
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
     * Applies the effect of the force floor when player enters.
     * 
     * @param player represents the player.
     * @param map represents the map the tile is in.
     */
    @Override
    public void onPlayerEnter(Player player, Maps map){
        switch(direction){
            case "Up":
                player.move('w', map);
                break;
            case "Down":
                player.move('s', map);
                break;
            case "Right":
                player.move('d', map);
                break;
            case "Left":
                player.move('a', map);
                break;
        }
    }

    /**
     * Returns the push direction of the force floor tile.
     * @return the direction via String (Right, Left, Up, Down).
     */
    public String getDirection(){
        return direction;
    }
}
