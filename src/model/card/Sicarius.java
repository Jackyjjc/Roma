package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Sicarius extends AbstractCard {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SICARIUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
}
