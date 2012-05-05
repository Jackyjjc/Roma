package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.GetCardFromPileState;
import framework.cards.Card;

class Aesculapinum extends AbstractCard implements ICardChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Aesculapinum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.AESCULAPINUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        //set up the states this card would go through
        GetCardFromPileState getCardState = new GetCardFromPileState(this, this);
        getCardState.setNextState(null);
        
        //run the initialState
        setState(getCardState);
    }
        
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }
        
        return isValid;
    }
}
