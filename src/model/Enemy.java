package model;

public class Enemy {
    private int xPosition;
    private int yPosition;
    private char direction;

    public Enemy(int xPosition, int yPosition, char direction) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
    }

    public void move(Maps map) {
        int nextX = this.xPosition;
        int nextY = this.yPosition;

        switch(direction) {
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
            switch(direction) {
                case 'w': direction = 's'; 
                break;
                case 'a': direction = 'd'; 
                break;
                case 's': direction = 'w'; 
                break;
                case 'd': direction = 'a'; 
                break;
            }
        }
    }

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
    
    public void onPlayerEnter(Player player, Maps map){
            player.killPlayer("caught by the enemy.");
    }
}