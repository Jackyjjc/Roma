package controller;

import gui.GraphicalView;
import model.Game;
import model.Notifier;

public class Controller {

    private static final int NUM_PLAYERS = 2;
    
    public static void main(String[] args) {
        
        Game g = new Game(NUM_PLAYERS);        
        Notifier notifier = g.getNotifier();
        GraphicalView view = new GraphicalView(g.getInputHandler());
        notifier.addListener(view);
        view.pack();
        view.setVisible(true);
    }
}
