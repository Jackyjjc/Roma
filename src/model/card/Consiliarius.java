package model.card;

import model.ICardResources;
import model.card.behaviour.ConsiliariusBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Consiliarius extends AbstractCard {
	
    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    
    private Consiliarius(ICardResources cardResources) {
        
        super(Card.CONSILIARIUS, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);

    }

    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Consiliarius(cardResources);
        card.setBehaviour(new ConsiliariusBehaviour(card));
        
        return card;
    }
}
