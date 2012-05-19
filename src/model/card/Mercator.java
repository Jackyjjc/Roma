package model.card;

import model.ICardResources;
import model.card.behaviour.MercatorBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Mercator extends AbstractCard {
    
    private static final int COST = 7;
    private static final int DEFENCE = 2;
    
    private Mercator(ICardResources cardResources) {
        
        super(Card.MERCATOR, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Mercator(cardResources);
        card.setBehaviour(new MercatorBehaviour(card));
        
        return card;
    }
}
