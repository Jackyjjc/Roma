package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.AssassinState;
import framework.cards.Card;

class Nero extends AbstractCard implements ICardChecker {
    
    private static final int COST = 8;
    private static final int DEFENCE = 9;
    
    Nero(ICardResources cardResources, IGameIO gameIO) {
        super(Card.NERO, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

    public void activate() {
        
        AssassinState destroy = new AssassinState(this, this);
        destroy.setNextState(null);
        
        setState(destroy);
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
