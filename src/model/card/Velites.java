package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.VelitesBehaviour;

public class Velites extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 3;

    private Velites(ICardResources cardResources) {

        super(Card.VELITES,
                CardType.CHARACTER,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Velites(cardResources);
        card.setBehaviour(new VelitesBehaviour(card));

        return card;
    }
}
