package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.BasilicaBehaviour;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Basilica extends AbstractCard implements IForumListener {

    private static final int COST = 6;
    private static final int DEFENCE = 5;

    private Basilica(ICardResources cardResources) {

        super(Card.BASILICA,
                CardType.BUILDING,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Basilica(cardResources);
        card.setBehaviour(new BasilicaBehaviour(card));

        return card;
    }

    public void alert() {
        ((IForumListener) getBehaviour()).alert();
    }

}
