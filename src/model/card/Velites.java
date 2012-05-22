package model.card;

import model.ICardResources;
import model.card.behaviour.AttackSelectedTargetBehaviour;
import framework.cards.Card;

public class Velites extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 3;

    private Velites() {

        super(Card.VELITES,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Velites card = new Velites();
        card.setBehaviour(new AttackSelectedTargetBehaviour(card, cardResources, CardType.CHARACTER));

        return card;
    }
}
