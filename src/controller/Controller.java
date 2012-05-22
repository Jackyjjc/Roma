package controller;

import gui.GraphicalView;
import model.Game;
import controller.gameState.DealCard;
import controller.gameState.LayCard;
import controller.gameState.PlayGame;
import controller.gameState.SwapCard;

public class Controller {

    private Game g;
    private GraphicalView view;
    private GuiInputHandler handler;
    
    public Controller() {
        handler = new GuiInputHandler();
    }

    public void run() {
        IGameState dealCard = new DealCard(g);
        IGameState swapCard = new SwapCard(g,view);
        IGameState layCard = new LayCard(g,view, handler);
        IGameState playGame = new PlayGame(g, view, handler);
        dealCard.setNextState(swapCard);
        swapCard.setNextState(layCard);
        layCard.setNextState(playGame);

        dealCard.run();
    }
    
    public void setGame(Game g) {
        this.g = g;
    }
    
    public void setView(GraphicalView view) {
        this.view = view;
    }
    
    public GuiInputHandler getInputHandler() {
        return handler;
    }
}
