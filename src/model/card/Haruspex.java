package model.card;

import model.ICardResources;
import model.ICardStorage;
import model.card.behaviour.RetrieveCardFromPileBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Haruspex extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 3;

    private Haruspex() {

        super(Card.HARUSPEX,
              CardType.CHARACTER,
              COST,
              DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources, ICardStorage deck) {

        Haruspex card = new Haruspex();
        card.setBehaviour(new RetrieveCardFromPileBehaviour(card, cardResources, deck, null));

        return card;
    }
}
