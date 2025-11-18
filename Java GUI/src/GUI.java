

//for image dictionary
import java.util.Map;
import java.util.HashMap;

import java.awt.GridLayout;
import java.awt.Color;

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

    private int rows = 20;
    private int cols = 20;

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

        displayLevel(level);
    }

    public void refreshMap(Level level) {
        Map map = level.getMap();

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

    public void loadImages(){
        imageMap = new HashMap<>();
        try {

            //tiles
            imageMap.put('#', new ImageIcon("images/wall.png")); //wall is missing
            imageMap.put('.', new ImageIcon("images/floor.png"));
            imageMap.put('F', new ImageIcon("images/fire.png"));
            imageMap.put('W', new ImageIcon("images/water.png"));
            imageMap.put('E', new ImageIcon("images/exit.png"));

            //items
            imageMap.put('k', new ImageIcon("images/redKey.png"));
            imageMap.put('K', new ImageIcon("images/blueKey.png"));
            imageMap.put('B', new ImageIcon("images/fireboots.png"));
            imageMap.put('P', new ImageIcon("images/flippers.png"));
            imageMap.put('M', new ImageIcon("images/Microchip.png"));

            //doors
            imageMap.put('d', new ImageIcon("images/redDoor.png"));
            imageMap.put('D', new ImageIcon("images/blueDoor.png"));

            //force floors
            imageMap.put('^', new ImageIcon("images/forceUp.png"));
            imageMap.put('>', new ImageIcon("images/forceRight.png"));
            imageMap.put('<', new ImageIcon("images/forceLeft.png"));
            imageMap.put('v', new ImageIcon("images/forceDown.png"));
        } catch(Exception e){
            System.err.println("Error loading images. Check file paths and image folder");
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Error loading game images.\n" + e.getMessage(), "Image Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshMap(){
        
    }


    public static void main(String[] args) {
        Level currentLevel = new Level(1);
        GUI gui = new GUI(currentLevel);

        if (currentLevel.isCompleted()) {
            currentLevel = new Level(2);
            gui.displayLevel(currentLevel);
        }
    }
}
