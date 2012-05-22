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

public class Aesculapinum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    private Aesculapinum() {

        super(Card.AESCULAPINUM,
                CardType.BUILDING,
                COST,
                DEFENCE);

    }
    
    static AbstractCard create(ICardResources cardResources) {

        Aesculapinum card = new Aesculapinum();
        ICardStorage discard = cardResources.getDiscardStorage();
        
        card.setBehaviour(new RetrieveCardFromPileBehaviour(card, cardResources, 
                                                            discard, CardType.CHARACTER));

        return card;
    }
}
