package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.MercatusBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Mercatus extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;

    private Mercatus(ICardResources cardResources) {

        super(Card.MERCATUS,
                CardType.BUILDING,
                COST,
                DEFENCE,
                cardResources);
    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Mercatus(cardResources);
        card.setBehaviour(new MercatusBehaviour(card));

        return card;
    }
}
