package model.card;

import java.util.List;

import model.ICardResources;
import model.IDisc;
import model.IField;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.MachinaActivator;

class Machina extends AbstractCard implements MachinaActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private List<AbstractCard> buildingCards;
    
    Machina(ICardResources cardResources, Notifier notifier) {
        
        super(Card.MACHINA, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
    }


    public void activate() {
        
        IField discs = getOwner().getField();
        List<AbstractCard> buildingCards = discs.removeCardsOf(CardType.BUILDING);

        for(AbstractCard card : buildingCards) {
            card.setCost(0);
        }
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }

    public void placeCard(Card name, int diceDisc) {
        
        AbstractCard card = findCard(name);
        IDisc disc = getOwner().getField().getDisc(diceDisc);
        
        card.lay(disc);
        
    }

    private AbstractCard findCard(Card name) {
        
        AbstractCard c = null;
        
        for(AbstractCard temp : buildingCards) {
            if(temp.getName() == name) {
                c = temp;
            }
        }
        
        return c;
    }

    public void complete() {
        for(AbstractCard card : buildingCards) {
            card.setCost(card.getDefaultCost());
        }
    }
}
