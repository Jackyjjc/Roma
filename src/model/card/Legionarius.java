package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Legionarius extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 5;
    
    Legionarius (ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.LEGIONARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
}
