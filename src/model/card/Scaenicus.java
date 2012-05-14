package model.card;

import model.ICardResources;
import model.IGameIO;
import framework.cards.Card;

class Scaenicus extends AbstractCard {

    private static final int COST = 8;
    private static final int DEFENCE = 3;

    
    Scaenicus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.SCAENICUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);

    }

}
