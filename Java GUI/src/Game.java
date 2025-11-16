import java.util.Scanner;

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
    char userInput;
    private Scanner sc;

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
        sc = new Scanner(System.in);
        levels = new Level[this.totalLevels];
        levels[0] = new Level(1);
        levels[1] = new Level(2);
        this.currentLevel = levels[currentLevelNum-1];
        this.player = new Player(levels[currentLevelNum-1].getPlayerStartX(), //sets player at starting point of a level
                            levels[currentLevelNum-1].getPlayerStartY());
        this.isRunning = false;
    }

    /**
     * Starts the game and the welcome display.
     * 
     * <p>
     * pre-condition: The game object should be properly initialized
     * post-condition: The game is running if player chooses to start, or else, game exits
     * </p>
     */
    public void startGame(){
        this.isRunning = true;

        displayWelcome();

        int input = -1; // placeholder in case until user inputs 1 or 0

        // input validation while loop to invalidate any other input other than 1 or 0.
        while (input != 0 && input != 1) {
            System.out.println("Shall we start? (1)-Yes, (0)-No: ");

            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine();

                if (input != 0 && input != 1) {
                System.out.println("Invalid input. Enter 1 for Yes, 0 for No.");
            }
            }
            else {
                System.out.println("Invalid input. Please enter 1 or 0.");
                sc.nextLine(); 
            }
        }

        // result of either 1 or 0 user input
        if (input == 1) {
            gameLoop();
        }
        else {
            System.out.println("Exiting game...");
            this.isRunning = false;
        }

        // Close scanner after game loop ends
        if (sc != null) {
            sc.close();
        }
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
        System.out.println("THANK YOU FOR PLAYING!");
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
        System.out.println("Loading Level " + levelNumber + "...");
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
     * Provides the main game loop when the game is active.
     * 
     * <p>
     * pre-condition: The game object is properly initialized. And currentLevel, Player, and Scanner are NOT null and are valid within the conditions
     * post-condition: Game loop continues unless player chooses to quit, lose, or they finish all 2 levels. 
     * </p>
     * <p>
     * This displays the game map, tied to the level. It processes user input as well as 
     * handles the player interactions with inventory items. Then it checks for win/lose conditions
     * </p>
     * 
     */
    public void gameLoop(){

        char userInput;

        while (this.isRunning){

            displayGame();
            
            // Check if scanner is still open before trying to read
            if (!sc.hasNext()) {
                break;
            }
            
            userInput = sc.next().charAt(0);
            sc.nextLine();

            processInput(userInput);

            // If game was ended by quit command, exit the loop
            if (!this.isRunning) {
                break;
            }

           
            Door door = currentLevel.getMap().getDoorAt(player.getXPosition(), player.getYPosition());
            Item item = currentLevel.getMap().getItemAt(player.getXPosition(), player.getYPosition());

            if (item != null && !item.getIsCollected()){
                player.collectItem(item); //collects item if there is item found in current player position
                currentLevel.getMap().removeItem(item); //remove item on game map
            }
            else if (door != null){
                door.onPlayerEnter(player, currentLevel.getMap());  //check for door at player position and handle entry
            }
           
            //get tile at player's position and call onPlayerEnter
            currentLevel.getMap().getTile(player.getXPosition(), player.getYPosition()).onPlayerEnter(player, currentLevel.getMap()); 

            if (checkLoseCondition()){
                System.out.println("Do you wish to reset or quit? (y/n): ");
                userInput = sc.next().charAt(0);
                sc.nextLine();

                switch (userInput){
                    case 'y':
                        resetGame();
                        break;
                    case 'n':
                        System.out.println("Exiting Game...");
                        endGame();
                        break;
                }
            }

            if (checkWinCondition()){
                System.out.println("LEVEL COMPLETE!");
                if (currentLevelNum == totalLevels){
                    System.out.println("YOU HAVE COMPLETED CHIP'S CHALLENGE!");
                    endGame();
                }
                else{
                    System.out.println("Press Enter to continue to next level...");
                    sc.nextLine();
                    nextLevel();
                }
            }
        }
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
        System.out.println("LEVEL RESET!");
    }

    /**
     * Checks if the win condition is met.
     * 
     * <p>
     * pre-condition: currentLevel and player should be initialized
     * post-condition: collected all microchips and enters the exit tile
     * </p>
     * @return true if win condition is met, false otherwise
     */
    public boolean checkWinCondition(){
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
        return !player.isAlive();
    }

    /**
     * Displays the current state of the game map and player info.
     * 
     * <p>
     * Displays the level number, the amount of microchips collected, the map itself which includes
     * all interactive items/elements in it.
     * </p>
     * 
     * <p>
     * pre-condition: Game object is initialized with a valid currentLevel and Player
     * post-condition: All is functional as to be displayed on the console
     * </p>
     */
    public void displayGame(){
        //used to mimick clearing the screen
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
        
        System.out.println("=====================================");
        System.out.println("    LEVEL " + currentLevelNum);
        System.out.println("    Chips: " + player.getMicrochips() + " / " + currentLevel.getRequiredChips());
        System.out.println("=====================================");
        
        Map map = currentLevel.getMap();
        int height = map.getHeight();
        int width = map.getWidth();
        
        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                char symbolToDisplay;
                
                if (player.getXPosition() == x && player.getYPosition() == y){
                    symbolToDisplay = '@'; 
                }
                else if (map.getDoorAt(x, y) != null){
                    symbolToDisplay = map.getDoorAt(x, y).getSymbol();
                }
                else if (map.getItemAt(x, y) != null && !map.getItemAt(x, y).getIsCollected()){
                    symbolToDisplay = map.getItemAt(x, y).getSymbol();
                }
                else if (map.getTile(x, y) != null){
                    symbolToDisplay = map.getTile(x, y).getSymbol();
                }
                else{
                    symbolToDisplay = '.';
                }
                
                System.out.print(symbolToDisplay + " "); 
            }
            System.out.println();
        }
        
        System.out.println("==============================================");
        System.out.println("Commands: W/A/S/D (move) | I (inventory) | R (reset) | Q (quit) | ! (Display Symbols) | H (How to play)");
        System.out.print("> ");
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
                player.getInventory().displayInventory();
                System.out.println("Press Enter to continue--");
                sc.nextLine();
                break;
            case 'r':
                resetGame();
                break;
            case 'q':
                endGame();
                break;
            case '!':
                displaySymbols();
                System.out.println("Press Enter to continue--");
                sc.nextLine();
                break;
            case 'h':
                displayHowToPlay();
                System.out.println("Press Enter to continue--");
                sc.nextLine();
                break;
            default:
                System.out.println("ERROR WRONG INPUT");
        }
    }

    /**
     * Displays the basic instructions and controls of the Chip's Challenge Game.
     * 
     */
    public void displayHowToPlay(){
        System.out.println("🧠 HOW TO PLAY:");
        System.out.println("- Navigate through the maze using the WASD keys.");
        System.out.println("- Collect all the chips (M) to unlock the exit (E).");
        System.out.println("- Some tiles are hazardous or require items to pass.");
        System.out.println("-------------------------------------");
    }

     /**
     * Displays the Symbol Legend as a guide for the User/Player.
     * 
     */
    public void displaySymbols(){
        System.out.println("📜 SYMBOL LEGEND:");
        System.out.printf("%-3s %-20s%n", "#", "Wall");
        System.out.printf("%-3s %-20s%n", ".", "Floor (empty / walkable)");
        System.out.printf("%-3s %-20s%n", "S", "Player Start position");
        System.out.printf("%-3s %-20s%n", "E", "Exit");
        System.out.printf("%-3s %-20s%n", "W", "Water tile");
        System.out.printf("%-3s %-20s%n", "F", "Fire tile");
        System.out.printf("%-3s %-20s%n", "K", "Blue Key");
        System.out.printf("%-3s %-20s%n", "k", "Red Key");
        System.out.printf("%-3s %-20s%n", "D", "Blue Door");
        System.out.printf("%-3s %-20s%n", "d", "Red Door");
        System.out.printf("%-3s %-20s%n", "B", "Fire Boots");
        System.out.printf("%-3s %-20s%n", "P", "Flippers");
        System.out.printf("%-3s %-20s%n", "M", "Microchip");
        System.out.printf("%-3s %-20s%n", "^", "Conveyor (Up)");
        System.out.printf("%-3s %-20s%n", "v", "Conveyor (Down)");
        System.out.printf("%-3s %-20s%n", "<", "Conveyor (Left)");
        System.out.printf("%-3s %-20s%n", ">", "Conveyor (Right)");
        System.out.printf("%-3s %-20s%n", "@", "You");
        System.out.println("-------------------------------------");
    }

    /**
     * 
     * Displays the Welcome Menu and the introduction screen that includes objectives and the basic controls for movement
     * 
     */
    public void displayWelcome(){
        //Welcome Page
        System.out.println("=====================================");
        System.out.println("          🧩 CHIP'S CHALLENGE 🧩");
        System.out.println("=====================================");
        System.out.println("Welcome to Chip's Challenge!");
        System.out.println("You are Chip, a clever student who must collect all the microchips");
        System.out.println("and reach the exit to prove your puzzle-solving skills!");
        System.out.println("-------------------------------------");

        // Objective
        System.out.println("OBJECTIVES:");
        System.out.println("- Collect all microchips in each level.");
        System.out.println("- Reach the exit to complete the stage.");
        System.out.println("- Avoid hazards like fire and water unless you have the right gear!");
        System.out.println("-------------------------------------");

        // Controls
        System.out.println("CONTROLS:");
        System.out.println("Use the following keys to move Chip:");
        System.out.println("    W - Move Up");
        System.out.println("    A - Move Left");
        System.out.println("    S - Move Down");
        System.out.println("    D - Move Right");
        System.out.println();
        System.out.println("Each move is done by typing a letter (W/A/S/D) and pressing Enter.");
        System.out.println("-------------------------------------");

        // How to Play
        displayHowToPlay();

        // Symbol Legend
        displaySymbols();
        
    }


}