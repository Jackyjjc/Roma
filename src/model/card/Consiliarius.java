package model.card;

import java.util.List;

import model.ICardStorage;
import model.IDisc;
import model.IField;
import model.Notifier;

class Consiliarius extends AbstractCard implements ConsiliariusActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private List<AbstractCard> charCards;
    
    Consiliarius(ICardStorage grave, Notifier notifier) {
        super(Card.CONSILIARUS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);

    }

    public void activate() {
      
        IField discs = getOwner().getField();
        charCards = discs.removeCardsOf(CardType.CHARACTER);
        
        for(AbstractCard card : charCards) {
            card.setCost(0);
        }
        
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }

    private AbstractCard findCard(Card name) {
        
        AbstractCard c = null;
        
        for(AbstractCard temp : charCards) {
            if(temp.getName() == name) {
                c = temp;
            }
        }
        
        return c;
    }
    
    public void placeCard(Card name, int diceDisc) {
        
        AbstractCard card = findCard(name);
        IDisc disc = getOwner().getField().getDisc(diceDisc);
        
        card.lay(disc);
    }

    public void complete() {
        for(AbstractCard card : charCards) {
            card.setCost(card.getDefaultCost());
        }
    }
    
}
