package controller;

import model.Game; //game class from model package
import view.GUI; //gui from view package

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter{
    private Game game;
    private GUI gui;
    
    public GameController(Game game, GUI gui){
        this.game = game;
        this.gui = gui;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        char keyChar = e.getKeyChar();
        game.processInput(keyChar); //update model
        gui.refreshMap(); //update GUI

        //check if any of the following happened and apply changes
        gui.checkGameState();
        }

}
