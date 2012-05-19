package model.card;

import model.ICardResources;
import model.card.behaviour.KatBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewd at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Kat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 1;
    
    private Kat(ICardResources cardResources) {
        
        super(Card.KAT, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Kat(cardResources);
        card.setBehaviour(new KatBehaviour(card));
        
        return card;
    }
}
