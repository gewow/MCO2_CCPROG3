public class WallTile extends Tile{
    public WallTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return false;
    }

    @Override
    public void onPlayerEnter(Player player, Map map){
        //
    }

    
}
