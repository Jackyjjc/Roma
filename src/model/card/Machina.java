package model.card;

import model.ICardResources;
import model.card.behaviour.RearrangerBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 20/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Machina extends AbstractCard implements ICardChecker {

    private static final int COST = 4;
    private static final int DEFENCE = 4;

    private Machina() {

        super(Card.MACHINA,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null && c.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Machina();
        card.setBehaviour(new RearrangerBehaviour(card, cardResources, CardType.BUILDING));

        return card;
    }
}
