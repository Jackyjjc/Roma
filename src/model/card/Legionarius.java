package model.card;

import model.DiceManager;
import model.ICardStorage;
import model.IPlayer;
import model.Notifier;

class Legionarius extends AbstractCard implements LegionariusActivator {

    private static final int COST = 4;
    private static final int DEFENCE = 5;
    
    private DiceManager roller;
    
    Legionarius (DiceManager roller, ICardStorage grave, Notifier notifier) {
        
        super(Card.LEGIONARIUS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
        this.roller = roller;
    }

    public void activate() {
        
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
    
    private AbstractCard getOppositeCard() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        
        int index = this.getDisc().getIndex();
        
        AbstractCard target = opponent.getField().getCard(index);
        
        return target;
    }

    public void giveAttackDieRoll(int roll) {
        
        AbstractCard target = getOppositeCard();
        
        Action.attack(target, roll);
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }
}
