package model.card;

import model.ICardResources;
import model.card.behaviour.AttackSelectedTargetBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Onager extends AbstractCard implements ICardChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 4;

    private Onager() {

        super(Card.ONAGER,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard target) {

        boolean isValid = false;
        if (target != null && target.getOwner() != null
                && target.getOwner() != getOwner()
                && target.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Onager card = new Onager();
        card.setBehaviour(new AttackSelectedTargetBehaviour(card, cardResources, card));

        return card;
    }
}
