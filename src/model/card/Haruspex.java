package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.HaruspexBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Haruspex extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 3;

    private Haruspex(ICardResources cardResources) {

        super(Card.HARUSPEX,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Haruspex(cardResources);
        card.setBehaviour(new HaruspexBehaviour(card));

        return card;
    }
}
