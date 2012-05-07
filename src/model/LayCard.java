package model;

import model.card.AbstractCard;


public class LayCard implements IListener {

    private Game g;
    private AbstractCard card;
    private IDisc disc;
    
    private LayCard(Game g) {
        this.g = g;
    }

    public static void initiate (Game g) {
        LayCard swapCard = new LayCard(g);
        g.getInputHandler().addInputListener(swapCard);
    }
    
    public void update() {
        // asdfasdf
        if (card == null) {
            card = g.getInputHandler().getCardInput();
        } else {
            disc = g.getInputHandler().getDiscInput();
            card.setCost(0);
            g.getCurrentPlayer().getHand().removeCard(card);
            layCard();
            card.setCost(card.getDefaultCost());
            card = null;
        }

        if (g.getCurrentPlayer().getHand().size() == 0) {
            g.advanceTurn();
            g.getNotifier().notifyListeners();
        }
    }

    private void layCard() {
        
        card.lay(disc);
        g.getNotifier().notifyListeners();
        
    }
}
