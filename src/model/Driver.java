/**
 * This represents the Driver that runs the actual game.
 * 
 * <p>
 * No other functions other than starting the game.
 * All other functionalities are held in Game class.
 * </p>
 * 
 * @author Anton Luis Galido
 * @version 1.0
 */

public class Driver {
    public static void main(String[] args){
        Game game = new Game();
        game.startGame();
    }

}
