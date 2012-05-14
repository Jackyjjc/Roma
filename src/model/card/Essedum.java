package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Essedum extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;

     
    Essedum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ESSEDUM, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }
   
}
