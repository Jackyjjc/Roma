package model.card.behaviour;
import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;
import model.card.IDieChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArchitectusBehaviour extends Behaviour implements ICardChecker {

    private List<AbstractCard> buildingCards;

    public ArchitectusBehaviour(AbstractCard host) {
        super(host);
    }

    public void initialise() {

        buildingCards = getHost().getOwner().getHand().getCardsOf(CardType.BUILDING);

        for(AbstractCard card : buildingCards) {
            card.setCost(0);
        }

    }

    public void complete() {

        for (AbstractCard card : buildingCards) {
            card.setCost(card.getDefaultCost());
            buildingCards.remove(card);
        }

    }

    public boolean isValidCard(AbstractCard card) {
        
        boolean isValid = false;
        
        if(card.getType() == CardType.BUILDING 
           && card.getOwner() != getHost().getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }
}


