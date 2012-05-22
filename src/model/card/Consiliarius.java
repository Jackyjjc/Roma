package model.card;

import model.ICardResources;
import model.card.behaviour.RearrangerBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Consiliarius extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;


    private Consiliarius() {

        super(Card.CONSILIARIUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Consiliarius card = new Consiliarius();
        card.setBehaviour(new RearrangerBehaviour(card, cardResources, CardType.CHARACTER));

        return card;
    }
}
