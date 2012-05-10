package model.card;

import java.util.List;

import model.ICardResources;
import model.IGameIO;
import model.card.state.ChooseCardState;
import model.card.state.LayCardState;
import model.card.state.SetCardCostFreeState;
import framework.cards.Card;

public class Machina extends AbstractCard implements ICardChecker {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private List<AbstractCard> buildingCards;
    
    Machina(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.MACHINA, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void activate() {
        
        buildingCards =  getOwner().getField().removeCardsOf(CardType.BUILDING);

        SetCardCostFreeState setCardFree = new SetCardCostFreeState(this, buildingCards);
        ChooseCardState chooseCard = new ChooseCardState(this, buildingCards);
        LayCardState layCard = new LayCardState(this, chooseCard, buildingCards);
        
        setCardFree.setNextState(chooseCard);
        chooseCard.setNextState(layCard);
        layCard.setNextState(chooseCard);
        
        setState(setCardFree);
        runState();
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }

    public List<AbstractCard> getFloatingCards () {
    	return buildingCards;
    }
    
}
