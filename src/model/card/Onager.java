package model.card;

import model.ICardResources;
import model.card.behaviour.OnagerBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Onager extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 4;
    
    private Onager(ICardResources cardResources) {
        
        super(Card.ONAGER, 
              CardType.BUILDING,
              COST, 
              DEFENCE,
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Onager(cardResources);
        card.setBehaviour(new OnagerBehaviour(card));
        
        return card;
    }
}
