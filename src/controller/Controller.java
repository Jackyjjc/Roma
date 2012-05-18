package controller;

import gui.GraphicalView;
import model.Game;
import controller.gameState.DealCard;
import controller.gameState.LayCard;
import controller.gameState.PlayGame;
import controller.gameState.SwapCard;

public class Controller {

    private static final int NUM_DICE = 3;
    private static final int START_NUM_CARDS = 4;

    private Game g;
    private GraphicalView view;
    private GuiInputHandler handler;
    
    public Controller(Game g, GraphicalView view, GuiInputHandler handler) {
        this.g = g;
        this.view = view;
        this.handler = handler;
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
    
}
