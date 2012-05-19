package model.card;

import model.ICardResources;
import model.card.behaviour.TelephoneBoxBehaviour;
import framework.cards.Card;

/**
 * 
 * @author Chris Fong
 * @author Junjie CHEN
 * 
 */

public class TelephoneBox extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;

    public TelephoneBox(ICardResources cardResources) {
        
        super(Card.TELEPHONEBOX, 
              CardType.BUILDING,
              COST, 
              DEFENCE, 
              cardResources);
    
    }

    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new TelephoneBox(cardResources);
        card.setBehaviour(new TelephoneBoxBehaviour(card, cardResources.getTurnMover()));
    
        return card;
    }
}
