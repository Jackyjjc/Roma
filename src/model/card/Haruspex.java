package model.card;

import model.ICardResources;
import model.IGameIO;
import model.card.state.GetCardFromPileState;
import framework.cards.Card;

class Haruspex extends AbstractCard implements ICardChecker {

    private static final int COST = 4;
    private static final int DEFENCE = 3;
    
    Haruspex(ICardResources cardResources, IGameIO gameIO) {
        super(Card.HARUSPEX, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        GetCardFromPileState getCard = new GetCardFromPileState(this, this, 
                                             getCardResources().getDeckStorage());
        getCard.setNextState(null);
        
        setState(getCard);
    }

    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(card != null) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
