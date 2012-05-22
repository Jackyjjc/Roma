package model.card;

import model.ICardResources;
import model.card.behaviour.LayForFreeBehaviour;
import framework.cards.Card;

public class Senator extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 3;

    private Senator() {

        super(Card.SENATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Senator card = new Senator();
        card.setBehaviour(new LayForFreeBehaviour(card, cardResources, CardType.CHARACTER));

        return card;
    }
}
