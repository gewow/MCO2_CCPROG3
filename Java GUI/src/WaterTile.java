public class WaterTile extends Tile{
    public WaterTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Map map){
        if (!player.getInventory().hasBoots("flippers")){
            player.killPlayer("drowned");
        }
    }
}
