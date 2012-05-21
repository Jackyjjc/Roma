package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.KatBehaviour;

/**
 * Reviewd at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Kat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 1;

    private Kat() {

        super(Card.KAT,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Kat();
        card.setBehaviour(new KatBehaviour(card, cardResources));

        return card;
    }

    public int getLives() {
        return ((KatBehaviour) super.getBehaviour()).getLives();
    }

}
