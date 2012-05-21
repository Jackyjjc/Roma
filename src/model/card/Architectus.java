package model.card;

import model.ICardResources;
import model.card.behaviour.LayForFreeBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Architectus extends AbstractCard implements ICardChecker {

    private static final int COST = 3;
    private static final int DEFENCE = 4;

    private Architectus() {

        super(Card.ARCHITECTUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard card) {

        boolean isValid = false;

        if (card.getType() == CardType.BUILDING
                && card.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {
        Architectus card = new Architectus();
        card.setBehaviour(new LayForFreeBehaviour(card, cardResources, CardType.BUILDING));
        return card;
    }

}
