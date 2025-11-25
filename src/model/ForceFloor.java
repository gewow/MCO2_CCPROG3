package model;

public class ForceFloor extends Tile implements EffectOnPlayer{
    private String direction;

    public ForceFloor(int xPosition, int yPosition, char symbol, String direction){
        super(xPosition, yPosition, symbol);
        this.direction = direction;
    }

    @Override
    public boolean canPlayerEnter(Player player){
        return true;
    }

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

    public String getDirection(){
        return direction;
    }
}
