package model.card;

import model.ICardStorage;
import model.Notifier;

class Gladiator extends AbstractCard implements GladiatorActivator {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    Gladiator(ICardStorage grave, Notifier notifier) {
        super(Card.GLADIATOR, CardType.CHARACTER,
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
        
        ICardStorage opponentHand;
        AbstractCard target = getOwner().getField().getCard(diceDisc);
        
        target.getDisc().removeCard();
        
        opponentHand = target.getOwner().getHand();
        opponentHand.pushCard(target);
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }
}
