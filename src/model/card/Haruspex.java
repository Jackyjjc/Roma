package model.card;

import model.ICardStorage;
import model.Notifier;


class Haruspex extends AbstractCard implements HaruspexActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 3;
    
    private ICardStorage deck;
    
    Haruspex(ICardStorage deck, ICardStorage grave, Notifier notifier) {
        super(Card.HARUSPEX, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
    }

    public void activate() {
        
    }
    
    private boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getOwner() == null) {
            isValid = true;
        }
        
        return isValid;
    }

    public void chooseCardFromPile(int indexOfCard) {

        AbstractCard card = deck.getCard(indexOfCard);
        ICardStorage hand = this.getOwner().getHand();
        
        deck.removeCard(card);
        deck.shuffle();
        hand.pushCard(card);
        
    }

    public void complete() {
        
    }
    
}
