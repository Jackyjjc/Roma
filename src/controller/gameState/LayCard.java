package controller.gameState;

import gui.GraphicalView;
import model.Game;
import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.CardFactory;
import controller.GuiInputHandler;
import controller.IGameState;
import controller.ILayCardListener;


public class LayCard implements ILayCardListener, IGameState {

    private Game g;
    private GraphicalView view;
    private GuiInputHandler handler;
    private IGameState next;

    private IPlayer firstPlayer;
    
    public LayCard(Game g, GraphicalView view, GuiInputHandler handler) {
        this.g = g;
        this.view = view;
        this.handler = handler;
        this.firstPlayer = g.getCurrentPlayer();
    }

    public void layCard(int fromIndex, int toIndex) {

        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = currentPlayer.getHand().getCard(fromIndex);
        IDisc disc = currentPlayer.getField().getDisc(toIndex);
        
        g.getCurrentPlayer().getHand().removeCard(card);

        card.setCost(0);
        card.lay(disc);
        card.setCost(card.getDefaultCost());
        
        if (g.getCurrentPlayer().getHand().size() == 0) {
            g.getTurnMover().advanceTurn();
            g.getNotifier().notifyListeners();
            if(firstPlayer == g.getCurrentPlayer()) {
                if(getNextState() != null) {
                    getNextState().run();
                }
            } else {
                view.showLayCardDialog();
            }
        } else {
            g.getNotifier().notifyListeners();
        }

    }

    public void run() {
        handler.setLayCardListener(this);
        view.showLayCardDialog();
    }

    public void setNextState(IGameState state) {
        next = state;
    }

    public IGameState getNextState() {
        return next;
    }
}
