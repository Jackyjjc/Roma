package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Praetorianus extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    Praetorianus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.PRAETORIANUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

}
