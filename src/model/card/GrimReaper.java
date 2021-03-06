package model.card;

import model.ICardResources;
import model.card.behaviour.GrimReaperBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class GrimReaper extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;

    private GrimReaper() {

        super(Card.GRIMREAPER,
                CardType.CHARACTER,
                COST,
                DEFENCE);
    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new GrimReaper();
        card.setBehaviour(new GrimReaperBehaviour(card, cardResources));

        return card;
    }
}
