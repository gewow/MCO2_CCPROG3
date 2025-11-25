package model;

public class WaterTile extends Tile implements EffectOnPlayer{
    public WaterTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("flippers")){
            player.killPlayer("drowned");
        }
    }
}
