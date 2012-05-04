package model.card;

import model.ICardResources;
import model.ICardStorage;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.AesculapinumActivator;

class Aesculapinum extends AbstractCard implements AesculapinumActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Aesculapinum(ICardResources cardResources, Notifier notifier) {
        super(Card.AESCULAPINUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        //nothing to do
    }
        
    private boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }

    public void chooseCardFromPile(int indexOfCard) {
        
        ICardStorage discard = getCardResources().getDiscardStorage();
        AbstractCard card = discard.getCard(indexOfCard);
        ICardStorage hand = this.getOwner().getHand();
        
        if (isValidCard(card)) {
        
            discard.removeCard(card);
            hand.pushCard(card);
            
        }    
    }

    public void complete() {
        //nothing to do here
    }
}
