package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.CenturioBehaviour;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Centurio extends AbstractCard {

    private static final int COST = 9;
    private static final int DEFENCE = 5;

    private Centurio(ICardResources cardResources) {

        super(Card.CENTURIO,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Centurio(cardResources);
        card.setBehaviour(new CenturioBehaviour(card));

        return card;
    }

}
