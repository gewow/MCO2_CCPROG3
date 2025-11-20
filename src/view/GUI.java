package view;
import model.*;

//for image dictionary
import java.util.Map;
import java.util.HashMap;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
//for keyboard listener
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

//for images
import javax.swing.ImageIcon;

public class GUI {

    private Game game; //holds the model (Game class)
    private Map<Character, ImageIcon> imageMap; //creates key value pairs for each character pertaining to associated image

    private JFrame frame; //window of the program
    private JPanel gridPanel; // panel that holds the grid of tiles
    private JLabel[][] tiles; //2d array of Jlabels representing all tiles

    private int rows = game.getMap().getWidth();
    private int cols = game.getMap().getHeight();

    public GUI(Level level) {
        frame = new JFrame("Chip's Challenge! 🤓");
        
        //close the app when 'X' is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Grid Panel area

        // Each cell in the grid will hold one JLabel
        gridPanel = new JPanel(new GridLayout(rows, cols));
        tiles = new JLabel[rows][cols];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {

                JLabel label = new JLabel();

                // Make the label opaque so background color is visible
                label.setOpaque(true);

                // Add a white border to each label for visual separation
                label.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                // Shade border tiles gray
                if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1) {
                    label.setBackground(Color.GRAY);
                } else {
                    label.setBackground(Color.LIGHT_GRAY);
                }

                tiles[x][y] = label;
                gridPanel.add(label);
            }
        }

        // Pack the frame so it sizes itself to fit the contents neatly
        frame.pack();

        // makes the window visible
        frame.setVisible(true);

        // add the panel containing the grid to the main window
        frame.add(gridPanel);

        // non full screen window size
        frame.setSize(800, 800);

        refreshMap(level);
    }

    public void refreshMap(Level level) {
        Maps map = level.getMap();

        Tile[][] tilesFromMap = map.getGrid();

        // Loop through every position in the map
        for (int x = 0; x < map.getHeight(); x++) {
            for (int y = 0; y < map.getWidth(); y++) {
                Tile t = tilesFromMap[x][y];    // Get the Tile at this position
                char symbol = t.getSymbol();    // Get its symbol (e.g., '#', '.', 'M', etc.)

                // Set JLabel text to the tile symbol
                tiles[x][y].setText(String.valueOf(symbol));
                tiles[x][y].setHorizontalAlignment(JLabel.CENTER); // Center the symbol
                tiles[x][y].setFont(new Font("Monospaced", Font.BOLD, 20)); // Monospaced font

                // Map tile symbols to background colors
                switch (symbol) {
                    case '#': tiles[x][y].setBackground(Color.GRAY); break;  // Wall
                    case '.': tiles[x][y].setBackground(Color.LIGHT_GRAY); break; // Floor
                    case 'F': tiles[x][y].setBackground(Color.ORANGE); break;        // Fire
                    case 'W': tiles[x][y].setBackground(Color.CYAN); break;       // Water
                    case 'M': tiles[x][y].setBackground(Color.YELLOW); break;     // Microchip
                    case 'S': tiles[x][y].setBackground(Color.GREEN); break;      // Player start
                    case 'E': tiles[x][y].setBackground(Color.RED); break;     // Exit
                    default: tiles[x][y].setBackground(Color.LIGHT_GRAY);         // Fallback
                }
            }
        }
    }

    //loads images into the system once
    public void loadImages(){
        imageMap = new HashMap<>();
        try {
            //player
            imageMap.put('@', new ImageIcon("resources/characters/chipStanding.png"));

            //tiles
            imageMap.put('#', new ImageIcon("resources/tiles/wall.png")); //wall is missing
            imageMap.put('.', new ImageIcon("resources/tiles/floor.png"));
            imageMap.put('F', new ImageIcon("resources/tiles/fire.png"));
            imageMap.put('W', new ImageIcon("resources/tiles/water.png"));
            imageMap.put('E', new ImageIcon("resources/tiles/exit.png"));

            //items
            imageMap.put('k', new ImageIcon("resources/items/redKey.png"));
            imageMap.put('K', new ImageIcon("resources/items/blueKey.png"));
            imageMap.put('B', new ImageIcon("resources/items/fireboots.png"));
            imageMap.put('P', new ImageIcon("resources/items/flippers.png"));
            imageMap.put('M', new ImageIcon("resources/items/Microchip.png"));

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

    public void refreshMap(){
        char symbol = '.';

        for (int y = 0; y < rows; y++){
            for (int x = 0; x < cols; x++){
                if (game.getPlayer().getXPosition() == x && game.getPlayer().getYPosition() == y){//is player here
                   symbol =  '@';
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

                ImageIcon icon =imageMap.get(symbol); //store the image extracted from dictionary as icon
                tiles[y][x].setIcon(icon); //use setIcon to paint image on current element
            }
        }
    }


    public static void main(String[] args) {
        Level currentLevel = new Level(1);
        GUI gui = new GUI(currentLevel);

        if (currentLevel.isCompleted()) {
            currentLevel = new Level(2);
            gui.refreshMap(currentLevel);
        }
    }
}
