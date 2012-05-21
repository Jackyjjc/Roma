package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.IDisc;
import model.IDiscListener;
import model.card.behaviour.TurrisBehaviour;

public class Turris extends AbstractCard implements IDiscListener {

    private static final int COST = 6;
    private static final int DEFENCE = 6;

    private Turris(ICardResources cardResources) {

        super(Card.TURRIS,
                CardType.BUILDING,
                COST,
                DEFENCE,
                cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Turris(cardResources);
        card.setBehaviour(new TurrisBehaviour(card));

        return card;
    }

    public void update(IDisc disc) {
        ((IDiscListener) getBehaviour()).update(disc);
    }

}
