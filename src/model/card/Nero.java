package model.card;

import model.ICardResources;
import model.card.behaviour.NeroBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Nero extends AbstractCard {
    
    private static final int COST = 8;
    private static final int DEFENCE = 9;
    
    private Nero(ICardResources cardResources) {
        
        super(Card.NERO, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);

    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Nero(cardResources);
        card.setBehaviour(new NeroBehaviour(card));
        
        return card;
    }
}
