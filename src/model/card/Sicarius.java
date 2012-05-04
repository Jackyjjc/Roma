package model.card;

import model.ICardResources;
import model.IPlayer;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.SicariusActivator;

class Sicarius extends AbstractCard implements SicariusActivator {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardResources cardResources, Notifier notifier) {
        super(Card.SICARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
    }

    
    public boolean isValidTarget(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = getOwner().getOpponent();
        
        AbstractCard target = opponent.getField().getCard(diceDisc);

        target.disCard();
        this.disCard();
    }
    
    public void complete() {
        //nothing to do here
    }
}
