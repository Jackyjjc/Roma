package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Onager extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 4;
    
    AbstractCard target;
    
    Onager(ICardResources cardResources, IGameIO gameIO) {
        super(Card.ONAGER, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }
   
}
