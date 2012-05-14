package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class TribunusPlebis extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    
    TribunusPlebis(ICardResources cardResources, IGameIO gameIO) {
        super(Card.TRIBUNUSPLEBIS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

}
