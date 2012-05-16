package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Aesculapinum extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    public Aesculapinum(ICardResources cardResources, IGameIO gameIO) {
        super(Card.AESCULAPINUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
        
    }

}
