package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Machina extends AbstractCard {

    private static final int COST = 4;
    private static final int DEFENCE = 4;
        
    Machina(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.MACHINA, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

 
}
