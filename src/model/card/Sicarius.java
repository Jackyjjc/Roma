package model.card;

import model.ICardResources;
import model.card.behaviour.KamikazeBehaviour;
import framework.cards.Card;

public class Sicarius extends AbstractCard implements ICardChecker {

    private static final int COST = 9;
    private static final int DEFENCE = 2;

    Sicarius() {

        super(Card.SICARIUS,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c.getOwner() != null && c.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Sicarius card = new Sicarius();
        card.setBehaviour(new KamikazeBehaviour(card, CardType.CHARACTER, cardResources, card));

        return card;
    }
}
