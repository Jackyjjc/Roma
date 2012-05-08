package model.card;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;

public class Kat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 1;
    
    Kat(ICardResources cardResources, IGameIO gameIO) {
        super(Card.KAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void activate() {
        // TODO Auto-generated method stub

    }

}
