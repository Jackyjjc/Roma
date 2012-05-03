package model.card;

import model.ICardStorage;
import model.Notifier;

class Aesculapinum extends AbstractCard implements AesculapinumActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Aesculapinum(ICardStorage grave, Notifier notifier) {
        super(Card.AESCULAPINUM, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
        
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
        
        AbstractCard input = getGrave().getCard(indexOfCard);
        ICardStorage hand = this.getOwner().getHand();
        
        if (isValidCard(input)) {
        
            getGrave().removeCard(input);
            hand.pushCard(input);
            
        }    
    }

    public void complete() {
        //nothing to do here
    }
}
