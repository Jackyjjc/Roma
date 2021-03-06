package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.TelephoneBoxBehaviour;

/**
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class TelephoneBox extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    public TelephoneBox() {

        super(Card.TELEPHONEBOX,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new TelephoneBox();
        card.setBehaviour(new TelephoneBoxBehaviour(card, cardResources, cardResources.getTurnMover()));

        return card;
    }
}
