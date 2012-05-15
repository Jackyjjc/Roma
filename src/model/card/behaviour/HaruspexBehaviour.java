package model.card.behaviour;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;

public class HaruspexBehaviour extends Behaviour {

    public HaruspexBehaviour(AbstractCard host) {
        super(host);
    }

   public void complete() {
        
        InputHandler handler = getHost().getGameIO().getInputHandler();
        ICardStorage hand = getHost().getOwner().getHand();
        ICardStorage deck = getHost().getCardResources().getDeckStorage();
        
        int index = handler.getIntInput();
        AbstractCard card = deck.getCard(index);
        
        if (isValidCard(card)) {
            deck.removeCard(card);
            deck.shuffle();
            hand.pushCard(card);       
        }
    }

    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(card != null) {
            isValid = true;
        }
        
        return isValid;
    }
}
