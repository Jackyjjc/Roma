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

public class Gladiator extends AbstractCard implements ICardChecker {

    private static final int COST = 6;
    private static final int DEFENCE = 5;

    private Gladiator() {

        super(Card.GLADIATOR,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target != null && target.getOwner() != getOwner()
                && target.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Gladiator card = new Gladiator();
        card.setBehaviour(new GladiatorBehaviour(card, cardResources, card));

        return card;
    }
}
