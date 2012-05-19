package model.card;

import model.ICardResources;
import model.card.behaviour.MachinaBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewed at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Machina extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
        
    private Machina(ICardResources cardResources) {
        
        super(Card.MACHINA, 
              CardType.BUILDING,
              COST, 
              DEFENCE, 
              cardResources);
        
    }

    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Machina(cardResources);
        card.setBehaviour(new MachinaBehaviour(card));
        
        return card;
    }
}
