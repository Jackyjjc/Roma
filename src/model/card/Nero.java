package model.card;

import model.ICardResources;
import model.card.behaviour.KamikazeBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Nero extends AbstractCard {

    private static final int COST = 8;
    private static final int DEFENCE = 9;

    private Nero() {

        super(Card.NERO,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Nero card = new Nero();
        card.setBehaviour(new KamikazeBehaviour(card, CardType.BUILDING, cardResources));

        return card;
    }
}
