package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Legat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Legat(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.LEGAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

}
