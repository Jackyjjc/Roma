package model.card;

import model.ICardResources;
import model.card.behaviour.AttackOppositeBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Legionarius extends AbstractCard implements ICardChecker {

    private static final int COST = 4;
    private static final int DEFENCE = 5;

    private Legionarius() {

        super(Card.LEGIONARIUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target.getOwner() != null
                && target.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }

    
    static AbstractCard create(ICardResources cardResources) {

        Legionarius card = new Legionarius();
        card.setBehaviour(new AttackOppositeBehaviour(card, cardResources, card));

        return card;
    }

}
