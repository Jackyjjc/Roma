package model;

import java.util.LinkedList;
import java.util.List;

import model.card.AbstractCard;

public class SwapCard implements IListener {

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
        g.getInputHandler().addInputListener(swapCard);
    }
    
    public void update() {
        
        System.out.println(count);
        
        if (count <= 1) {
            AbstractCard card = g.getInputHandler().getCardInput();
            g.getCurrentPlayer().getHand().removeCard(card);
            cardsToSwap.add(card);
            g.getNotifier().notifyListeners();
        }
        
        if (count == 1) {
            g.advanceTurn();
            g.getNotifier().notifyListeners();
        }
        
        if(count > 1 && count <= 3) {
            AbstractCard card = g.getInputHandler().getCardInput();
            g.getCurrentPlayer().getHand().removeCard(card);
            cardsToSwap.add(card);
            g.getNotifier().notifyListeners();
        }
    
        if (count == 3) {
            swapCard();
            g.advanceTurn();
            g.getNotifier().notifyListeners();
            g.getInputHandler().removeInputListener(this);
        }
        
        count++;
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
