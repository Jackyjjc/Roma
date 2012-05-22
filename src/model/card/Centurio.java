package model.card;

import model.ICardResources;
import model.card.behaviour.CenturioBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Centurio extends AbstractCard  {

    private static final int COST = 9;
    private static final int DEFENCE = 5;

    private Centurio() {

        super(Card.CENTURIO,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Centurio card = new Centurio();
        card.setBehaviour(new CenturioBehaviour(card, cardResources));

        return card;
    }

}
