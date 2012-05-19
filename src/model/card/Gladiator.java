package model.card;

import model.ICardResources;
import model.card.behaviour.GladiatorBehaviour;
import framework.cards.Card;

/**
 * 
 * Reviewd at 20/05/2012
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 *
 */

public class Gladiator extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    private Gladiator(ICardResources cardResources) {
        
        super(Card.GLADIATOR, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Gladiator(cardResources);
        card.setBehaviour(new GladiatorBehaviour(card));
        
        return card;
    }
}
