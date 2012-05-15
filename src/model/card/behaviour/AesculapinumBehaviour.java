package model.card.behaviour;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

/**
 * 
 * @author Chris Fong
 * @author Jacky CHEN
 *
 */

public class AesculapinumBehaviour extends Behaviour {

    public AesculapinumBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();
        ICardStorage hand = getHost().getOwner().getHand();
        ICardStorage discard = getHost().getCardResources().getDiscardStorage();

        int index = handler.getIntInput();
        AbstractCard card = discard.getCard(index);

        if (isValidCard(card)) {
            discard.removeCard(card);
            hand.pushCard(card);
        }
    }

    public boolean isValidInt(int index) {
        
        boolean isValid = false;
        
        ICardStorage discard = getHost().getCardResources().getDiscardStorage();
        AbstractCard card = discard.getCard(index);
        
        if(discard.size() > index) {
            if(isValidCard(card)) {
                isValid = true;
            }
        }
        
        return isValid;
    }
    
    private boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }

}
