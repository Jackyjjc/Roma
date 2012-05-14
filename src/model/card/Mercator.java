package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Mercator extends AbstractCard {
    
    private static final int COST = 7;
    private static final int DEFENCE = 2;
    
    Mercator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
}
