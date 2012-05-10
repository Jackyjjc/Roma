package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.AttackOpponentState;
import framework.cards.Card;

class Legionarius extends AbstractCard implements ICardChecker {

    private static final int COST = 4;
    private static final int DEFENCE = 5;
    
    Legionarius (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.LEGIONARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void activate() {
        
        AttackOpponentState attack = new AttackOpponentState(this, this);
        attack.setNextState(null);
        
        setState(attack);
    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }

}
