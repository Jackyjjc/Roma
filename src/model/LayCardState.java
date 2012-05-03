package model;

import model.card.AbstractCard;

public class LayCardState {

/*    private Game g;
    private InputHandler handler;
    
    public LayCardState(Game g) {
        this.handler = g.getInputHandler();
    }

    public void run() {
        
        IPlayer player = g.getCurrentPlayer();
        int numPlayers = g.getNumPlayers();
        
        ICardStorage hand = null;
        IDisc disc = null;
        AbstractCard card = null;
        
        for(int i = 0 ; i < numPlayers; i++) {
            
            hand = player.getHand();
            
            while(hand.size() != 0) {
                card = handler.getCardInput();
                disc = handler.getDiscInput();
                
                card.setCost(0);
                card.lay(disc);
                card.setCost(card.getDefaultCost());
            }
            
            player = player.getOpponent();
        }
        
        g.setState(new GameRunningState(g));
    }
    */
}
