package model.card;

import model.ICardStorage;
import model.IPlayer;
import model.Notifier;

class Sicarius extends AbstractCard implements SicariusActivator {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardStorage grave, Notifier notifier) {
        super(Card.SICARIUS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
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
