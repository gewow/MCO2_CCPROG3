package controller;

import model.Game; //game class from model package
import view.GUI; //gui from view package

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles the user input and updates the game state as well as the GUI.
 * 
 * <p>
 * This is the controller in an MVC format. Checks for keyboard 'events'
 * to move the player and for action events like the restart button.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 */
public class GameController extends KeyAdapter implements ActionListener{
    private Game game;
    private GUI gui;
    
    /**
     * Constructs a GameController with the game model and the view with GUI.
     * 
     * @param game the game model to be controlled
     * @param gui the GUI to be updated
     */
    public GameController(Game game, GUI gui){
        this.game = game;
        this.gui = gui;
    }
    
    /**
     * Handles keyboard input.
     * 
     * <p>
     * Updates the game model based on the clicked key then refreshes the GUI
     * and checks the current game state.
     * </p>
     * 
     * @param e the keyEvent containing info about the key clicked.
     */
    @Override
    public void keyPressed(KeyEvent e){
        char keyChar = e.getKeyChar();
        game.processInput(keyChar); //update model
        gui.refreshMap(); //update GUI
        gui.checkGameState(); //check what is happening to player
    }

    /**
     * Handles the restart button click event.
     * 
     * <p>
     * Resets the game, then refreshes the map again, then makes sure
     * the focus is returned to the main window.
     * </p>
     * 
     * @param e the action event triggered by the restart button
     */
    @Override
    public void actionPerformed(ActionEvent e){
        game.resetGame();
        gui.refreshMap();
        gui.requestFocusToFrame();
    }

}
