package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.AssassinState;
import framework.cards.Card;

class Sicarius extends AbstractCard implements ICardChecker {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SICARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        AssassinState assassin = new AssassinState(this, this);
        assassin.setNextState(null);
        
        setState(assassin);
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getOwner()
           && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }

}
