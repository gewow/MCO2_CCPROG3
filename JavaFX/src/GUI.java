import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame frame;
    private JPanel gridPanel;
    private JLabel[][] tiles;

    private int rows = 20;
    private int cols = 20;

    public GUI() {
        frame = new JFrame("Chip's Challenge! 🤓");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Grid Panel area
        gridPanel = new JPanel(new GridLayout(rows, cols));
        tiles = new JLabel[rows][cols];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
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

        frame.pack();
        frame.setVisible(true);
        frame.add(gridPanel);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
