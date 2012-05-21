package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.SenatorBehaviour;

public class Senator extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 3;

    private Senator(ICardResources cardResources) {

        super(Card.SENATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Senator(cardResources);
        card.setBehaviour(new SenatorBehaviour(card));

        return card;
    }
}
