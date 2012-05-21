package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.MercatorBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Mercator extends AbstractCard {

    private static final int COST = 7;
    private static final int DEFENCE = 2;

    private Mercator() {

        super(Card.MERCATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Mercator();
        card.setBehaviour(new MercatorBehaviour(card, cardResources));

        return card;
    }
}
