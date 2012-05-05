package model.card;

import model.Die;
import model.ICardResources;
import model.IGameIO;
import model.card.state.CenturioRollDieState;
import framework.cards.Card;

class Centurio extends AbstractCard implements ICardChecker, IDieChecker {

    private static final int COST = 9;
    private static final int DEFENCE = 5;
    
    Centurio (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.CENTURIO, CardType.CHARACTER,
                COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        CenturioRollDieState rollDie = new CenturioRollDieState(this);
        
        setState(rollDie);
    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
    
    public boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }
}
