package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Gladiator extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    
    Gladiator(ICardResources cardResources, IGameIO gameIO) {
        super(Card.GLADIATOR, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }

}
