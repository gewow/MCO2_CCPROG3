
package view;

import model.Enemy;
import model.Game;
import model.Level;
import model.Inventory;

//for image dictionary
import java.util.Map;
import java.util.HashMap;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
//for keyboard listener
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JButton;

//for images
import javax.swing.ImageIcon;

/**
 * this GUI class handles the graphical interface for our Chip's challenge game.
 * 
 * <p>
 * Displays the game map, player, enemies, items, and ingame stats. 
 * Updates the display according to the game state.
 * </p>
 * 
 * @version 1.0
 * 
 * @author Melangelo Guanzon
 * @author Anton Luis Galido
 * 
 * 
 */
public class GUI {

    private Timer gameTimer;
    private Game game; //holds the model (Game class)
    private Map<Character, ImageIcon> imageMap; //creates key value pairs for each character pertaining to associated image

    private JFrame frame; //window of the program
    private JPanel gridPanel; // panel that holds the grid of tiles
    private JLabel[][] tiles; //2d array of Jlabels representing all tiles

    private int rows;
    private int cols;

    private JLabel levelLabel;
    private JLabel chipsLabel;
    private JLabel keysLabel;
    private JLabel bootsLabel;
    private JButton restartButton;

    /**
     * Constructs the GUI and initializes all of the components.
     * 
     * <p>
     * precondition: game object is initialized.
     * postcondition: JFrame and all panels/labels are initialized
     * </p>
     * @param game the game model controlling all the game logic.
     */
    public GUI(Game game) {
        this.game = game;
        this.rows = game.getCurrentLevel().getMap().getHeight();
        this.cols = game.getCurrentLevel().getMap().getWidth();
        loadImages();

        frame = new JFrame("Chip's Challenge! 🤓");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the app when 'X' is clicked


        // Each cell in the grid will hold one JLabel
        gridPanel = new JPanel(new GridLayout(rows, cols));
        tiles = new JLabel[rows][cols];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {

                JLabel label = new JLabel();
                label.setOpaque(true); // Make the label opaque so background color is visible

                // Add a white border to each label for visual separation
                label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                label.setHorizontalAlignment(JLabel.CENTER);

                tiles[x][y] = label;
                gridPanel.add(label);
            }
        }



        //Inventory Panel 
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.DARK_GRAY);
        infoPanel.setPreferredSize(new java.awt.Dimension(150, 800));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        levelLabel = new JLabel("Level: 1");
        chipsLabel = new JLabel("Chips: 0/7");
        keysLabel = new JLabel("Keys: R:0 B:0");
        bootsLabel = new JLabel("Boots: None");
        restartButton = new JButton("RESTART");

        levelLabel.setForeground(Color.WHITE);
        chipsLabel.setForeground(Color.WHITE);
        keysLabel.setForeground(Color.WHITE);
        bootsLabel.setForeground(Color.WHITE);

        levelLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        chipsLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        keysLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        bootsLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        restartButton.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        restartButton.setBackground(Color.GRAY);
        restartButton.setForeground(Color.BLACK);
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.setPreferredSize(new Dimension(150, 40));
        restartButton.setFocusable(false);
        

        infoPanel.add(levelLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(chipsLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(keysLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(bootsLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(restartButton);
        infoPanel.add(Box.createVerticalGlue());

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(gridPanel, java.awt.BorderLayout.CENTER);
        frame.add(infoPanel, java.awt.BorderLayout.EAST);
        
        frame.setMinimumSize(new java.awt.Dimension(900, 800));
        frame.setPreferredSize(new java.awt.Dimension(1000, 850));
        frame.pack();

        frame.setLocationRelativeTo(null);

        refreshMap();
        
        frame.setVisible(true);
        frame.setResizable(true);

        gameTimer = new Timer(300, e -> {
            if (game.isRunning() && game.isPlayerAlive()){
                game.updateEnemies();
                checkCollisions();
                refreshMap();
                checkGameState();
            }
        });
        gameTimer.start();
    }

    /**
     * Attaches a keyboard listener to the main frame.
     * 
     * <p>
     * precondition: listener is not null
     * postcondition: listener is added to frame  and focus is requested.
     * </p>
     * 
     * @param listener the keyAdapter handling the keyboard input.
     */
    public void attachKeyListener(KeyAdapter listener){
        frame.addKeyListener(listener);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    /**
     * Attaches an ActionListener to the restart button.
     * 
     * <p>
     * precondition: listener is not null.
     * postcondition: restart button is triggered when listener is clicked.
     * </p>
     * 
     * @param listener this is the ActionListener that's triggered when restart is clicked.
     */
    public void attachRestartButton(ActionListener listener){
        restartButton.addActionListener(listener);
    }

    /**
     * Request focus for the main window so that the keyboard inputis captured.
     */
    public void requestFocusToFrame(){
        frame.requestFocusInWindow();
    }

    /**
     * Checks if there's a collision between the player and enemy
     * 
     * <p>
     * Pre-condition: game and player initialized  
     * Post-condition: player is killed if collision detected.
     * </p>
     * 
     */
    public void checkCollisions(){
        for (Enemy enemy : game.getEnemies()){
            if (enemy.getXPosition() == game.getPlayer().getXPosition() &&
                enemy.getYPosition() == game.getPlayer().getYPosition()) {
                    game.getPlayer().killPlayer("caught by enemy");
                }
        }
    }
    


    /**
     * Loads all the images to the image dictionary.
     * 
     * This is the logic that maps the character Array from previous MCO project, to image icons.
     */
    public void loadImages(){
        imageMap = new HashMap<>();
        try {
            //player
            imageMap.put('u', new ImageIcon("resources/characters/chipForward.png"));
            imageMap.put('s', new ImageIcon("resources/characters/chipStanding.png")); //s for south
            imageMap.put('l', new ImageIcon("resources/characters/chipLeft.png"));
            imageMap.put('r', new ImageIcon("resources/characters/chipRight.png"));
            imageMap.put('1', new ImageIcon("resources/characters/drown.png")); 
            imageMap.put('2', new ImageIcon("resources/characters/burn.png"));
            imageMap.put('3', new ImageIcon("resources/characters/chipSwimRight.png"));
            imageMap.put('4', new ImageIcon("resources/characters/chipSwimLeft.png"));
            imageMap.put('5', new ImageIcon("resources/characters/chipSwimForward.png"));
            imageMap.put('6', new ImageIcon("resources/characters/chipSwim.png"));

            //enemy 
            imageMap.put('↑', new ImageIcon("resources/characters/monsterUp.png"));
            imageMap.put('↓', new ImageIcon("resources/characters/monster.png")); //s for south
            imageMap.put('←', new ImageIcon("resources/characters/monsterLeft.png"));
            imageMap.put('→', new ImageIcon("resources/characters/monsterRight.png"));

            //tiles
            imageMap.put('#', new ImageIcon("resources/tiles/wall.png")); 
            imageMap.put('.', new ImageIcon("resources/tiles/floor.png"));
            imageMap.put('F', new ImageIcon("resources/tiles/fire.png"));
            imageMap.put('W', new ImageIcon("resources/tiles/water.png"));
            imageMap.put('E', new ImageIcon("resources/tiles/exitEnabled.png"));
            imageMap.put('O', new ImageIcon("resources/tiles/teleporterUnlocked.png"));
            imageMap.put('I', new ImageIcon("resources/tiles/ice.png"));


            //items
            imageMap.put('k', new ImageIcon("resources/items/redKey.png"));
            imageMap.put('K', new ImageIcon("resources/items/blueKey.png"));
            imageMap.put('B', new ImageIcon("resources/items/fireboots.png"));
            imageMap.put('P', new ImageIcon("resources/items/flippers.png"));
            imageMap.put('M', new ImageIcon("resources/items/Microchip.png"));
            imageMap.put('T', new ImageIcon("resources/items/teleporterBoots.png"));

            //doors
            imageMap.put('d', new ImageIcon("resources/doors/redDoor.png"));
            imageMap.put('D', new ImageIcon("resources/doors/blueDoor.png"));

            //force floors
            imageMap.put('^', new ImageIcon("resources/tiles/forceUp.png"));
            imageMap.put('>', new ImageIcon("resources/tiles/forceRight.png"));
            imageMap.put('<', new ImageIcon("resources/tiles/forceLeft.png"));
            imageMap.put('v', new ImageIcon("resources/tiles/forceDown.png"));
        } catch(Exception e){
            System.err.println("Error loading images. Check file paths and image folder");
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Error loading game images.\n" + e.getMessage(), "Image Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Redraws the game grid depending on the current map state including all items, the player, enemies, etc.
     * 
     * <p>
     * precondition: game and map initialized
     * postcondition: gridPanel displays updated tiles , characters, items
     * </p>
     */
    public void refreshMap(){
        char symbol = '.';

        for (int y = 0; y < rows; y++){
            for (int x = 0; x < cols; x++){

                boolean enemyHere = false;

                for (Enemy enemy : game.getEnemies()){
                    if (enemy.getXPosition() == x && enemy.getYPosition() == y){
                        symbol = enemy.getSpriteChar();
                        enemyHere = true;
                        break;
                    }
                }

                if (!enemyHere){
                    if (game.getPlayer().getXPosition() == x && game.getPlayer().getYPosition() == y){//is player here
                        symbol =  game.getPlayer().getPlayerOrientation();
                    }
                    else if (game.getMap().getDoorAt(x, y) != null){ //is there a door here
                        symbol = game.getMap().getDoorAt(x, y).getSymbol();
                    }
                    else if (game.getMap().getItemAt(x, y) != null && !game.getMap().getItemAt(x, y).getIsCollected()){
                        symbol = game.getMap().getItemAt(x, y).getSymbol(); //is there an item here
                    }
                    else if (game.getMap().getTile(x, y) != null){
                        symbol = game.getMap().getTile(x, y).getSymbol(); //is this a tile
                    }
                }

                ImageIcon icon = imageMap.get(symbol); //store the image extracted from dictionary as icon
                tiles[y][x].setIcon(icon); //use setIcon to paint image on current element
            }
        }
        updateInfoPanel();
    }

    /**
     * Updates the information panel that shows chips, boots, keys, and level num.
     */
    public void updateInfoPanel(){
        Inventory inv = game.getPlayer().getInventory();
        String boots = "";

        levelLabel.setText("Level " + game.getCurrentLevel().getLevelNumber());
        chipsLabel.setText("Chips: " + game.getPlayer().getMicrochips() + "/" + game.getCurrentLevel().getRequiredChips());
        
        keysLabel.setText("Keys R: " + inv.getRedKeys() + " B: " + inv.getBlueKeys());

        if (inv.hasBoots("flippers")){
            boots += "Flippers ";
        }

        if (inv.hasBoots("fireboots")){
            boots += "Fireboots ";
        }

        if (inv.hasBoots("teleportboots")){
            boots += "teleportboots";
        }
        
        bootsLabel.setText("Boots: " + (boots.isEmpty() ? "None" : boots));
    }

    /**
     * Checks whether the player finished the level or died.
     * 
     * Then displays the appropriate messages and handles level transitions or even reset.
     * 
     * <p>
     * Pre-condition: game is running  
     * </p>
     * 
     */
    public void checkGameState(){
        int currentLevel, choice;
        if (game.isLevelCompleted(game.getPlayer())){
            gameTimer.stop();

            currentLevel = game.getCurrentLevel().getLevelNumber();
            if (currentLevel < 2){
                choice = JOptionPane.showConfirmDialog(frame, "level " + currentLevel + " Complete! Proceed to next level?", "Level Complete!", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION){
                    game.nextLevel();
                    refreshMap();
                    gameTimer.start();
                } else {
                    game.endGame();
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Congratulations! You've completed all levels!", "Game Complete!", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        }

        if (!game.isPlayerAlive()){
            gameTimer.stop();



            choice = JOptionPane.showConfirmDialog(frame, "You died! Restart level?", "Game Over", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION){
                game.resetGame();
                refreshMap();
                gameTimer.start();
            } else {
                game.endGame();
                frame.dispose();
            }
        }
    }
}
