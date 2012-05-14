package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Consul extends AbstractCard {
	
    private static final int COST = 3;
    private static final int DEFENCE = 3;
    
    Consul(ICardResources cardResources, IGameIO gameIO) {
        super(Card.CONSUL, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
}
