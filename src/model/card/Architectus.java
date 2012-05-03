package model.card;

import java.util.List;

import model.ICardStorage;
import model.Notifier;

class Architectus extends AbstractCard implements ArchitectusActivator {

    private static final int COST = 3;
    private static final int DEFENCE = 4;

    private List<AbstractCard> buildingCards;
    
    Architectus(ICardStorage grave, Notifier notifier) {
        super(Card.ARCHITECTUS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
    }

    public void activate() {
        
        buildingCards = getOwner().getHand().getCardsOf(CardType.BUILDING);
        
        for(AbstractCard card : buildingCards) {
            card.setCost(0);
        }

    }

    public void complete() {
        for(AbstractCard card : buildingCards) {
            card.setCost(card.getDefaultCost());
        }
    }  
}
