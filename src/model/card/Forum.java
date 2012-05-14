package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Forum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    
    Forum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.FORUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
    }
    
}
