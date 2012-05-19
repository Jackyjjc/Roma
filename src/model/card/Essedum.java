package model.card;

import model.ICardResources;
import model.card.behaviour.EssedumBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewd at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Essedum extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
     
    private Essedum(ICardResources cardResources) {
        
        super(Card.ESSEDUM, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);

    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Essedum(cardResources);
        card.setBehaviour(new EssedumBehaviour(card, cardResources.getTurnMover()));
        
        return card;
    }
}
