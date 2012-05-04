package model.card;

import model.ICardResources;
import model.IPlayer;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.OnagerActivator;

class Onager extends AbstractCard implements OnagerActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 4;
    
    AbstractCard target;
    
    Onager(ICardResources cardResources, Notifier notifier) {
        super(Card.ONAGER, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
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
