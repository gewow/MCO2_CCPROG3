package model;

public class FireTile extends Tile implements EffectOnPlayer{
    public FireTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Maps map){
        if (!player.getInventory().hasBoots("fireboots")){
            player.killPlayer("caught on fire");
        }
    }
}