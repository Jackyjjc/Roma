package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.ArchitectusBehaviour;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Architectus extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 4;

    private Architectus(ICardResources cardResources) {

        super(Card.ARCHITECTUS,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {
        AbstractCard card = new Architectus(cardResources);
        card.setBehaviour(new ArchitectusBehaviour(card));
        return card;
    }

}
