package model;

public class ExitTile extends Tile{
    public ExitTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Maps map){
        //Level check completion will handle this
    }
}
