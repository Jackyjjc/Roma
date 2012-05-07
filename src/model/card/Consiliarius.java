package model.card;

import java.util.List;

import model.ICardResources;
import model.IGameIO;
import model.card.state.ChooseCardState;
import model.card.state.LayCardState;
import model.card.state.SetCardCostFreeState;
import framework.cards.Card;

class Consiliarius extends AbstractCard implements ICardChecker{

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private List<AbstractCard> charCards;
    
    Consiliarius(ICardResources cardResources, IGameIO gameIO) {
        super(Card.CONSILIARIUS, CardType.CHARACTER,
               COST, DEFENCE, cardResources, gameIO);

    }

    public void activate() {

        charCards = getOwner().getField().removeCardsOf(CardType.CHARACTER);
        
        SetCardCostFreeState setCardFree = new SetCardCostFreeState(this, charCards);
        ChooseCardState chooseCard = new ChooseCardState(this, charCards, this);
        LayCardState layCard = new LayCardState(this, chooseCard);
        
        setCardFree.setNextState(layCard);
        chooseCard.setNextState(layCard);
        layCard.setNextState(chooseCard);
        
        setState(setCardFree);
        runState();
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.CHARACTER && charCards.contains(c)) {
            isValid = true;
        }
        
        return isValid;
    }    
}
