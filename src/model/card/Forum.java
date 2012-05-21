package model.card;

import framework.cards.Card;
import model.Die;
import model.ICardResources;
import model.card.behaviour.ForumBehaviour;

/**
 * Reviewd at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Forum extends AbstractCard implements IDieChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private Forum() {

        super(Card.FORUM,
                CardType.BUILDING,
                COST,
                DEFENCE);
    }

    public boolean isValidDie(Die die) {

        boolean isValid = false;

        if (die != null && !die.isUsed()) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Forum card = new Forum();
        card.setBehaviour(new ForumBehaviour(card, cardResources, card));

        return card;
    }
}
