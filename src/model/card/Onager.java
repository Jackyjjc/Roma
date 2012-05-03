package model.card;

import model.ICardStorage;
import model.IPlayer;
import model.Notifier;

class Onager extends AbstractCard implements OnagerActivator {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    AbstractCard target;
    
    Onager(ICardStorage grave, Notifier notifier) {
        super(Card.MERCATUS, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
        
    }
    
    public void activate() {

    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }

    public void chooseDiceDisc(int diceDisc) {
        
        IPlayer opponent = getOwner().getOpponent();
        
        target = opponent.getField().getCard(diceDisc);
    }

    public void giveAttackDieRoll(int roll) {
        Action.attack(target, roll);
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }
    
}
