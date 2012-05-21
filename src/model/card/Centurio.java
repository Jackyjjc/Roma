package model.card;

import framework.cards.Card;
import model.Die;
import model.ICardResources;
import model.card.behaviour.CenturioBehaviour;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Centurio extends AbstractCard implements ICardChecker, IDieChecker {

    private static final int COST = 9;
    private static final int DEFENCE = 5;

    private Centurio() {

        super(Card.CENTURIO,
                CardType.CHARACTER,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target.getOwner() != null
                && target.getOwner() != getOwner()) {
            isValid = true;
        }

        return isValid;
    }

    public boolean isValidDie(Die die) {
        return !die.isUsed();
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Centurio card = new Centurio();
        card.setBehaviour(new CenturioBehaviour(card, cardResources, card, card));

        return card;
    }

}
