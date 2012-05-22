package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.card.behaviour.TribunusPlebisBehaviour;

public class TribunusPlebis extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;

    private TribunusPlebis() {

        super(Card.TRIBUNUSPLEBIS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new TribunusPlebis();
        card.setBehaviour(new TribunusPlebisBehaviour(card, cardResources));

        return card;
    }
}
