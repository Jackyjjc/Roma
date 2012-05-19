package model.card;

import model.ICardResources;
import model.card.behaviour.LegionariusBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Legionarius extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 5;
    
    private Legionarius (ICardResources cardResources) {
        
        super(Card.LEGIONARIUS, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Legionarius(cardResources);
        card.setBehaviour(new LegionariusBehaviour(card));
        
        return card;
    }
    
}
