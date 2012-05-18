package controller.gameState;

import model.Game;
import model.ICardStorage;
import model.IPlayer;
import model.card.AbstractCard;
import controller.IGameState;
import framework.Rules;

public class DealCard implements IGameState {

    private static final int START_NUM_CARDS = 5;
    private Game g;
    private IGameState next;
    
    public DealCard(Game g) {
        this.g = g;
    }
    
    public void run() {
        
        ICardStorage deck = g.getDeckStorage();
        
        IPlayer player = g.getCurrentPlayer();
        
        for(int i = 0; i < Rules.NUM_PLAYERS; i++) {
            
            for(int j = 0; j < START_NUM_CARDS; j++) {
                AbstractCard drawnCard = deck.popCard();
                player.getHand().pushCard(drawnCard);
            }
            
            player = player.getOpponent();
        }
        
        g.getNotifier().notifyListeners();
        
        if(getNextState() != null) {
            getNextState().run();
        }
    }

    public void setNextState(IGameState state) {
        next = state;
    }

    public IGameState getNextState() {
        return next;
    }
    
}
