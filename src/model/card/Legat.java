package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.LegatBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Legat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    private Legat(ICardResources cardResources) {

        super(Card.LEGAT,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);
    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Legat(cardResources);
        card.setBehaviour(new LegatBehaviour(card));

        return card;
    }

}
