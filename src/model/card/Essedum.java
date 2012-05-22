package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.EssedumBehaviour;

/**
 * Reviewd at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Essedum extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;

    private Essedum() {

        super(Card.ESSEDUM,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Essedum();
        card.setBehaviour(new EssedumBehaviour(card, cardResources, cardResources.getTurnMover()));

        return card;
    }
}
