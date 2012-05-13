package model.card.attribute;

import model.ICardResources;
import model.ICardStorage;
import model.IField;
import model.IGameIO;
import model.card.AbstractCard;
import model.card.CardType;
import framework.cards.Card;

public class Machina extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private ICardStorage buildingCards;
    
    Machina(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.MACHINA, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

    public void initialise() {

        IField field = getOwner().getField();

        buildingCards = field.removeCardsOf(CardType.BUILDING);

        for (AbstractCard c : buildingCards) {
            c.setCost(0);
        }

        getGameIO().getInputHandler().setList(buildingCards);
    }
    
    public void complete() {

        for (AbstractCard c : buildingCards) {
            c.setCost(c.getDefaultCost());
        }
        
        getGameIO().getInputHandler().setList(getOwner().getHand());
    }
    
    public boolean isValidCard(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c != null && c.getType() == CardType.BUILDING) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
