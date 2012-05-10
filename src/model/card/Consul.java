package model.card;

import model.Die;
import model.ICardResources;
import model.IGameIO;
import model.card.state.SetDieState;
import framework.cards.Card;

class Consul extends AbstractCard implements IDieChecker {
    
    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    Consul(ICardResources cardResources, IGameIO gameIO) {
        super(Card.CONSUL, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        SetDieState setDie = new SetDieState(this, this);
        setDie.setNextState(null);
        
        setState(setDie);
        
    }

    public boolean isValidDie(Die die) {
        
        boolean isValid = false;
        
        if(die != null && !die.isUsed()) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
