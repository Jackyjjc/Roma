package model.card;

import java.util.List;

import model.ICardStorage;
import model.Notifier;

class Senator extends AbstractCard implements SenatorActivator {

    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> charCards;
    
    Senator(ICardStorage grave, Notifier notifier) {
        super(Card.SENATOR, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
    }

    public void activate() {
        
        charCards = getOwner().getHand().getCardsOf(CardType.CHARACTER);
        
        for(AbstractCard card : charCards) {
            card.setCost(0);
        }
        
    }
    
    private boolean isValidCard (AbstractCard c) {
        
        boolean isValidCard = false;
        
        if (c.getType() == CardType.CHARACTER) {
            isValidCard = true;
        }
        
        return isValidCard;
        
    }
    
    public void complete() {

        for(AbstractCard card : charCards) {
            card.setCost(card.getDefaultCost());
        }
        
    }

}
