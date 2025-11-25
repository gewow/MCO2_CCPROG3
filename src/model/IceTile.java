package model;

public class IceTile extends Tile implements EffectOnPlayer{

    public IceTile(int xPosition, int yPosition, char symbol){
        super(xPosition, yPosition, symbol);
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

    @Override
    public void onPlayerEnter(Player player, Maps map){
        char direction = player.getLastDirection();

        if (direction == 'w' || direction == 'a' || direction == 's' || direction == 'd') {
            player.move(direction, map);
        }
    }
}
