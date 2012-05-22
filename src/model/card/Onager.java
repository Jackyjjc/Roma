package model.card;

import model.ICardResources;
import model.card.behaviour.AttackSelectedTargetBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Onager extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 4;

    private Onager() {

        super(Card.ONAGER,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Onager card = new Onager();
        card.setBehaviour(new AttackSelectedTargetBehaviour(card, cardResources, CardType.BUILDING));

        return card;
    }
}
