package model;

import model.card.AbstractCard;

public class DealCardState implements State {
    
    private static final int NUM_START_CARDS = 4;
    
    private Game g;
    
    public DealCardState(Game g) {
        this.g = g;
    }
    
    public void run() {
        
        ICardStorage deck = g.getDeckStorage();
        
        IPlayer player = g.getCurrentPlayer();
        int numPlayers = g.getNumPlayers();
        
        for(int i = 0; i < numPlayers; i++) {
            
            for(int j = 0; j < NUM_START_CARDS; j++) {
                AbstractCard drawnCard = deck.popCard();
                player.getHand().pushCard(drawnCard);
            }
            
            player = player.getOpponent();
        }
        
        g.getNotifier().notifyListeners();
        
        //g.setState(new SwapCardState(g));
    }

}
