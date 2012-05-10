package model.card;

import java.util.List;

import model.ICardResources;
import model.IGameIO;
import model.card.state.SetCardCostDefaultState;
import model.card.state.SetCardCostFreeState;
import framework.cards.Card;

class Senator extends AbstractCard{

    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    private List<AbstractCard> charCards;
    
    Senator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SENATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        charCards = getOwner().getHand().getCardsOf(CardType.CHARACTER);
        
        SetCardCostFreeState setCardFree = new SetCardCostFreeState(this, charCards);
        SetCardCostDefaultState setCardDefault = new SetCardCostDefaultState(this, charCards);
        
        setCardFree.setNextState(setCardDefault);
        setCardDefault.setNextState(null);
       
        setState(setCardFree);
        
    }

}
