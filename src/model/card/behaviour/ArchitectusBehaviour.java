package model.card.behaviour;

import java.util.List;

import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class ArchitectusBehaviour extends Behaviour implements ICardChecker {

    private List<AbstractCard> buildingCards;

    public ArchitectusBehaviour(AbstractCard host) {
        super(host);
    }

    public void initialise() {

        buildingCards = getOwner().getHand().getCardsOf(CardType.BUILDING);

        for (AbstractCard card : buildingCards) {
            card.setCost(0);
        }

    }

    public void complete() {

        for (AbstractCard card : buildingCards) {
            card.setCost(card.getDefaultCost());
        }

        buildingCards.clear();
    }

    public boolean isValidCard(AbstractCard card) {

        boolean isValid = false;

        if (card.getType() == CardType.BUILDING
                && card.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }
}


