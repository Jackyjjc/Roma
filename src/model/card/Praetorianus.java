package model.card;

import model.ICardResources;
import model.card.behaviour.PraetorianusBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Praetorianus extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    private Praetorianus(ICardResources cardResources) {
        
        super(Card.PRAETORIANUS, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Praetorianus(cardResources);
        card.setBehaviour(new PraetorianusBehaviour(card));
        
        return card;
    }
}
