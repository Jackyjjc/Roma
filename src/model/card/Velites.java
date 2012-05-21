package model.card;

import model.ICardResources;
import model.card.behaviour.AttackSelectedTargetBehaviour;
import framework.cards.Card;

public class Velites extends AbstractCard implements ICardChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 3;

    private Velites() {

        super(Card.VELITES,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;

        if (c.getOwner() != null && c.getOwner() != getOwner()
                && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Velites card = new Velites();
        card.setBehaviour(new AttackSelectedTargetBehaviour(card, cardResources, card));

        return card;
    }
}
