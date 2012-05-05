package model.card;

import java.util.List;

import model.ICardResources;
import model.IGameIO;
import model.card.state.SetCardCostDefaultState;
import model.card.state.SetCardCostFreeState;
import framework.cards.Card;

class Architectus extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 4;

    private List<AbstractCard> buildingCards;
    
    Architectus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ARCHITECTUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        buildingCards = getOwner().getHand().getCardsOf(CardType.BUILDING);
        
        SetCardCostFreeState setCardFree = new SetCardCostFreeState(this, buildingCards);
        SetCardCostDefaultState setCardDefault = new SetCardCostDefaultState(this, buildingCards);
        
        setCardFree.setNextState(setCardDefault);
        setCardDefault.setNextState(null);
       
        setState(setCardFree);

    }
 
}
