package model.card;

import model.ICardResources;
import model.card.behaviour.ForumBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewd at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Forum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    
    private Forum(ICardResources cardResources) {
        
        super(Card.FORUM, 
              CardType.BUILDING,
              COST, 
              DEFENCE, 
              cardResources);
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Forum(cardResources);
        card.setBehaviour(new ForumBehaviour(card));
        
        return card;
    }
}
