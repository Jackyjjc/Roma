package model;

import model.card.AbstractCard;


public class LayCard implements ILayCardInputListener {

    private IPlayer firstPlayer;
    private Game g;
    
    private LayCard(Game g) {
        this.g = g;
        this.firstPlayer = g.getCurrentPlayer();
    }

    public static void initiate (Game g) {
        LayCard layCard = new LayCard(g);
        g.getInputHandler().addLayCardListener(layCard);
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
            g.advanceTurn();
            if(firstPlayer == g.getCurrentPlayer()) {
                g.getInputHandler().removeLayCardListener();
                PlayGame.initiate(g);
            }
        }
        g.getNotifier().notifyListeners();
    }
}
