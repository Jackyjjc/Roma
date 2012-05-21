package model.card;

import model.ICardResources;
import model.card.behaviour.LayForFreeBehaviour;
import framework.cards.Card;

public class Senator extends AbstractCard implements ICardChecker {

    private static final int COST = 3;
    private static final int DEFENCE = 3;

    private Senator() {

        super(Card.SENATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard card) {

        boolean isValid = false;

        if (card != null && card.getOwner() == getOwner()
                && card.getType() == CardType.CHARACTER) {

            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Senator card = new Senator();
        card.setBehaviour(new LayForFreeBehaviour(card, cardResources, CardType.CHARACTER));

        return card;
    }
}
