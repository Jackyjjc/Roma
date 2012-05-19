package model.card;

import model.ICardResources;
import model.card.behaviour.AesculapinumBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 19/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Aesculapinum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    private Aesculapinum(ICardResources cardResources) {
        
        super(Card.AESCULAPINUM, 
              CardType.BUILDING,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {

        AbstractCard card = new Aesculapinum(cardResources);
        card.setBehaviour(new AesculapinumBehaviour(card));
        
        return card;
    }
}
