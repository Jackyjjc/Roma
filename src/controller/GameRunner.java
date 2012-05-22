package controller;

import model.Game;
import framework.Rules;
import gui.GraphicalView;

public class GameRunner {
    
    public static void main(String[] args) {
        
        final Controller gameController = new Controller();
        
        final Game g = new Game(Rules.NUM_PLAYERS);
        
        GraphicalView graphicalView = new GraphicalView(g, gameController.getInputHandler(),g);
        g.getNotifier().addListener(graphicalView);
        graphicalView.pack();
        graphicalView.setVisible(true);
        
        gameController.setView(graphicalView);
        gameController.setGame(g);
        gameController.run();
        
    }

}
