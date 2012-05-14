package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Centurio extends AbstractCard {

    private static final int COST = 9;
    private static final int DEFENCE = 5;
    
    Centurio (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.CENTURIO, CardType.CHARACTER,
                COST, DEFENCE, cardResources, gameIO);
        
    }

}
