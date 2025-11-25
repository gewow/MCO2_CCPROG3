package model;

public class FloorTile extends Tile{
    public FloorTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }
}
