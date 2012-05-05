package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.MimicState;
import framework.cards.Card;

class Scaenicus extends AbstractCard implements ICardChecker{

    private static final int COST = 8;
    private static final int DEFENCE = 3;
    
    private CardFactory factory;
    
    Scaenicus(ICardResources cardResources, IGameIO gameIO, CardFactory factory) {
        super(Card.SCAENICUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
        this.factory = factory;
    }

    public void activate() {
        
        MimicState mimic = new MimicState(this, this, factory);
        
        setState(mimic);
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c.getType() == CardType.CHARACTER 
           && c.getOwner() == this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
}
