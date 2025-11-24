package main;

import model.Game;
import model.Level;
import view.GUI;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        GameController controller = new GameController(game, gui);

        gui.attachKeyListener(controller);
        gui.attachRestartButton(controller);
    }
}
