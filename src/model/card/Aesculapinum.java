package model.card;

import model.ICardResources;
import model.ICardStorage;
import model.card.behaviour.RetrieveCardFromPileBehaviour;
import framework.cards.Card;

/**
 * Reviewed at 19/05/2012
 *
 * @author Chris Fong
 * @author Junjie CHEN
 */

public class Aesculapinum extends AbstractCard implements ICardChecker {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    private Aesculapinum() {

        super(Card.AESCULAPINUM,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }
    
    static AbstractCard create(ICardResources cardResources) {

        Aesculapinum card = new Aesculapinum();
        ICardStorage discard = cardResources.getDiscardStorage();
        
        card.setBehaviour(new RetrieveCardFromPileBehaviour(card, cardResources, discard, card));

        return card;
    }
}
