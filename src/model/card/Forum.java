package model.card;

import model.ICardResources;
import model.card.behaviour.ForumBehaviour;
import framework.cards.Card;

/**
 * Reviewd at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Forum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private Forum() {

        super(Card.FORUM,
                CardType.BUILDING,
                COST,
                DEFENCE);
    }

    
    static AbstractCard create(ICardResources cardResources) {

        Forum card = new Forum();
        card.setBehaviour(new ForumBehaviour(card, cardResources));

        return card;
    }
}
