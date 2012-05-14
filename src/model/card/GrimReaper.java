package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class GrimReaper extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    public GrimReaper(ICardResources cardResources, IGameIO gameIO) {
        super(Card.GRIMREAPER, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

}
