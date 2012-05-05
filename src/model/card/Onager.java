package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.AttackSelectedTargetState;
import framework.cards.Card;

class Onager extends AbstractCard implements ICardChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 4;
    
    AbstractCard target;
    
    Onager(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ONAGER, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void activate() {
        AttackSelectedTargetState attack = new AttackSelectedTargetState(this, this);
        attack.setNextState(null);
        
        setState(attack);
    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
