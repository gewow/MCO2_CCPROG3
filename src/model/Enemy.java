package model;

public class Enemy extends Character implements EffectOnPlayer{
    private char direction;

    public Enemy(int xPosition, int yPosition, char direction) {
        super(xPosition, yPosition);
        this.direction = direction;
    }

    @Override
    public void move(char direction, Maps map) {
        int nextX = this.xPosition;
        int nextY = this.yPosition;

        switch(this.direction) {
            case 'w':
                nextX--;
                break;
            case 'a':
                nextY--;
                break;
            case 's':
                nextX++;
                break;
            case 'd':
                nextY++;
                break;
        }

        if (canMoveTo(nextX, nextY, map)){
            this.xPosition = nextX;
            this.yPosition = nextY;
        }
        else {
            // reverses direction
            switch(this.direction) {
                case 'w': this.direction = 's'; 
                break;
                case 'a': this.direction = 'd'; 
                break;
                case 's': this.direction = 'w'; 
                break;
                case 'd': this.direction = 'a'; 
                break;
            }
        }
    }

    @Override
    public boolean canMoveTo(int x, int y, Maps map) { 
        //use the map's isValidPosition method to check if the player is moving within the coordinates of the map
        if (!map.isValidPosition(x, y)){
            return false;
        }
        
        Tile tile = map.getTile(x, y);

        //safety check for null tiles
        if (tile == null){
            return false;
        }

        // if wall tile, move enemy to opposite direction
        if (tile.getSymbol() == '#') {
            return false;
        }

        return true;
    }
    
    @Override
    public void onPlayerEnter(Player player, Maps map){
            player.killPlayer("caught by the enemy.");
    }

    public char getDirection(){
        return direction;
    }

    public char getSpriteChar(){
        switch (direction){
            case 'w':
                return '↑';
            case 'a':
                return '←';
            case 's':
                return '↓';
            case 'd':
                return '→';
            default:
                return ':';
        }
    }
}