package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Nero extends AbstractCard {
    
    private static final int COST = 8;
    private static final int DEFENCE = 9;
    
    Nero(ICardResources cardResources, IGameIO gameIO) {
        super(Card.NERO, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

}
