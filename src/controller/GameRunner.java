package controller;

import framework.Rules;
import gui.GraphicalView;
import model.Game;

public class GameRunner {
    
    public static void main(String[] args) {
        
        Game g = new Game(Rules.NUM_PLAYERS);
        GuiInputHandler handler = new GuiInputHandler();
        GraphicalView graphicalView = new GraphicalView(g,handler);
        g.getNotifier().addListener(graphicalView);
        
        graphicalView.pack();
        graphicalView.setVisible(true);
        
        Controller gameController = new Controller(g,graphicalView, handler);
        gameController.run();
    }

}
