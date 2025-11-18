
/**
 * 
 * This represents the main game logic.
 * 
 * <p>
 * It handles the main components like level, the movement of the player, the status of the game,
 * and the visual components as well.
 * </p>
 * 
 * @author Melangelo Guazon
 * @author Anton Galido
 * 
 * @version 1.0
 */
public class Game {
    private Level currentLevel;
    private Player player;
    private boolean isRunning;
    private int totalLevels;
    private int currentLevelNum;
    private Level[] levels;

    /**
     * Constructs a new game object, and initalizes the levels and player as well.
     * 
     * <p>
     * sets the current level num to 1 and puts the player in the start position.
     * Also initializes the Scanner (sc) for user input
     * </p>
     */
    public Game(){
        this.currentLevelNum = 1;
        this.totalLevels = 2;
        levels = new Level[this.totalLevels];
        levels[0] = new Level(1);
        levels[1] = new Level(2);
        this.currentLevel = levels[currentLevelNum-1];
        this.player = new Player(levels[currentLevelNum-1].getPlayerStartX(), //sets player at starting point of a level
                            levels[currentLevelNum-1].getPlayerStartY());
        this.isRunning = true;
    }


    /**
     * Ends the game and displays the farewell print statement.
     * 
     * <p>
     * pre-condition: The game is running
     * post-condition: The game is stopped
     * </p>
     */
    public void endGame(){
        this.isRunning = false;
    }

    /**
     * Loads a specific level.
     * 
     * <p>
     * pre-condition: level number should be greater than 0 and no more than 2
     * post-condition: current level is set to the specified and player is reset
     * </p>
     * @param levelNumber the specific level num to be loaded
     * @param resetLevel whether to reset the level (true for restart, false for first time)
     */
    public void loadLevel(int levelNumber, boolean resetLevel){
        this.currentLevel = levels[levelNumber-1];
        
        if (resetLevel) {
            currentLevel.reset();
        }
        
        player.resetPlayer(currentLevel.getMap(), currentLevel.getPlayerStartX(), currentLevel.getPlayerStartY());
    }

    /**
     * Moves to the next level.
     * <p>
     * pre-condition: current level is less than totalLevels
     * post-condition: current level number is incremented then next level is loaded
     * </p>
     */
    public void nextLevel(){
        this.currentLevelNum++;
        loadLevel(this.currentLevelNum, false); // false = don't reset, it's a new level
    }
    
    
    /**
     * Resets the current level and the player.
     * 
     * <p>
     * pre-conditions: The currentLevel and player should be initialized
     * post-conditions: They are reset
     * </p>
     */
    public void resetGame(){
        loadLevel(currentLevelNum, true); // true = reset the current level
    }


    public Map getMap(){
        return currentLevel.getMap();
    }

    public Player getPlayer(){
        return player;
    }

    public boolean isRunning(){
        return isRunning;
    }

    /**
     * Checks if the win condition for the level is met.
     * 
     * <p>
     * pre-condition: currentLevel and player should be initialized
     * post-condition: collected all microchips and enters the exit tile
     * </p>
     * @return true if win condition is met, false otherwise
     */
    public boolean isLevelCompleted(Player player){
        return currentLevel.checkCompletion(player);
    }

    /**
     * Checks if the lose condition is met.
     * 
     * <p>
     * pre-condition: player should be initialized
     * post-condition: player is dead in the current game
     * </p>
     * @return true if lose condition is met, false otherwise
     */
    public boolean checkLoseCondition(){
        return !isPlayerAlive();
    }

    public boolean isPlayerAlive(){
        return player.isAlive();
    }


    /**
     * Processes the user input and executes depending on the specified character
     * 
     * <p>
     * pre-condition: input is a valid char representing a command
     * post-condition: the action is performed successfully
     * </p>
     * @param input the character input of the player
     */
    public void processInput(char input){
        input = Character.toLowerCase(input);
        switch (input){
            case 'w':
                player.move(input, currentLevel.getMap());
                break;
            case 'a':
                player.move(input, currentLevel.getMap());
                break;
            case 's':
                player.move(input, currentLevel.getMap());
                break;
            case 'd':
                player.move(input, currentLevel.getMap());
                break;
            case 'i':
                break;
            case 'r':
                resetGame();
                break;
            case 'q':
                endGame();
                break;
            case '!':
                break;
            case 'h':                
                break;
            default:
        }
    }
}
    

     