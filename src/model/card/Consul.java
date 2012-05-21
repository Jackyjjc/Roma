package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.ConsulBehaviour;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Consul extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 3;

    private Consul() {

        super(Card.CONSUL,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Consul();
        card.setBehaviour(new ConsulBehaviour(card, cardResources));

        return card;
    }
}
