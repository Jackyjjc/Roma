package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

public class Consiliarius extends AbstractCard {
	
    private static final int COST = 4;
    private static final int DEFENCE = 4;
    
    
    Consiliarius(ICardResources cardResources, IGameIO gameIO) {
        super(Card.CONSILIARIUS, CardType.CHARACTER,
               COST, DEFENCE, cardResources, gameIO);

    }

}
