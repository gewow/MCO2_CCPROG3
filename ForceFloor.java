public class ForceFloor extends Tile{
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
    public void onPlayerEnter(Player player, Map map){
        switch(direction){
            case "up":
                player.move('w', map);
                break;
            case "down":
                player.move('s', map);
                break;
            case "right":
                player.move('d', map);
                break;
            case "left":
                player.move('a', map);
                break;
        }
    }
}
