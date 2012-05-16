package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Haruspex extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 3;
    
    Haruspex(ICardResources cardResources, IGameIO gameIO) {
        super(Card.HARUSPEX, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
}
