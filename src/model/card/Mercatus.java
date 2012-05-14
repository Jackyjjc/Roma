package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Mercatus extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    Mercatus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATUS, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
    }

}
