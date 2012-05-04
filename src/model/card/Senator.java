package model.card;

import java.util.List;

import model.ICardResources;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.SenatorActivator;

class Senator extends AbstractCard implements SenatorActivator {

    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> charCards;
    
    Senator(ICardResources cardResources, Notifier notifier) {
        super(Card.SENATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
        charCards = getOwner().getHand().getCardsOf(CardType.CHARACTER);
        
        for(AbstractCard card : charCards) {
            card.setCost(0);
        }
        
    }
    
    public void complete() {

        for(AbstractCard card : charCards) {
            card.setCost(card.getDefaultCost());
        }
        
    }

}
