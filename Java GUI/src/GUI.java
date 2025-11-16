import javax.swing.*;
import java.awt.*;

public class GUI {

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

    public void displayLevel(Level level) {
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
    public static void main(String[] args) {
        Level currentLevel = new Level(1);
        GUI gui = new GUI(currentLevel);

        if (currentLevel.isCompleted()) {
            currentLevel = new Level(2);
            gui.displayLevel(currentLevel);
        }
    }
}
