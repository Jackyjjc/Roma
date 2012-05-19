package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Architectus extends AbstractCard {

    private static final int COST = 3;
    private static final int DEFENCE = 4;
    
    Architectus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ARCHITECTUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
 
}
