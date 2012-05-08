package model;

import java.util.LinkedList;
import java.util.List;

import model.card.AbstractCard;
import framework.cards.Card;

public class SwapCard implements ISwapCardInputListener {

    private Game g;
    private List<AbstractCard> cardsToSwap;
    private int count;
    
    private SwapCard(Game g) {
        this.g = g;
        this.count = 0;
        this.cardsToSwap = new LinkedList<AbstractCard>();
    }

    public static void initiate (Game g) {
        SwapCard swapCard = new SwapCard(g);
        g.getInputHandler().addSwapListener(swapCard);
    }
    
    public void update(Card[] cards) {
        
        IPlayer currentPlayer = g.getCurrentPlayer();
        AbstractCard card = null;
        
            
        for (Card c : cards) {
            card = currentPlayer.getHand().getCard(c);
            cardsToSwap.add(card);
            currentPlayer.getHand().removeCard(card);
        }

        g.advanceTurn();
        
        if(cardsToSwap.size() == 4) {
            swapCard();
            g.getInputHandler().removeSwapListener();
        }
        
        g.getNotifier().notifyListeners();
    }
    
    private void swapCard() {
        
        IPlayer player = g.getPlayer(1);
        
        
        for(int i = 0; i < 2; i++) {
            player.getHand().pushCard(cardsToSwap.remove(0));
        }
        
        player = player.getOpponent();

        for(int i = 0; i < 2; i++) {
            player.getHand().pushCard(cardsToSwap.remove(0));
        }
        
    }
}
