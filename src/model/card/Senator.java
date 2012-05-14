package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Senator extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    Senator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SENATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

}
