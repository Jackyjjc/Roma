package model.card;

import model.ICardStorage;
import model.IPlayer;
import model.Notifier;

class Velites extends AbstractCard implements VelitesActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 3;
    
    private AbstractCard target;
    
    Velites (ICardStorage grave, Notifier notifier) {
        super(Card.VELITES, CardType.CHARACTER,
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
        
        target = opponent.getField().getCard(diceDisc);
    }

    public void giveAttackDieRoll(int roll) {
        
        Action.attack(target, roll);
        
    }

    public void complete() {
        //nothing to do here
    }
}
