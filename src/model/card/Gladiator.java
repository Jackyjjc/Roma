package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.GladiatorBehaviour;

/**
 * Reviewd at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Gladiator extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 5;

    private Gladiator() {

        super(Card.GLADIATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Gladiator card = new Gladiator();
        card.setBehaviour(new GladiatorBehaviour(card, cardResources));

        return card;
    }
}
