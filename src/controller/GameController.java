package controller;

import model.Game; //game class from model package
import view.GUI; //gui from view package

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter implements ActionListener{
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
        gui.checkGameState(); //check what is happening to player
    }

    @Override
    public void actionPerformed(ActionEvent e){
        game.resetGame();
        gui.refreshMap();
        gui.requestFocusToFrame();
    }

}
