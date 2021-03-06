package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.PraetorianusBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Praetorianus extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;

    private Praetorianus() {

        super(Card.PRAETORIANUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Praetorianus();
        card.setBehaviour(new PraetorianusBehaviour(card, cardResources));

        return card;
    }
}
