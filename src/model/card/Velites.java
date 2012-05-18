package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Velites extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 3;
    
    Velites (ICardResources cardResources, IGameIO gameIO) {
        super(Card.VELITES, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

}
