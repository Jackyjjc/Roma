package model.card.attribute;

import framework.cards.Card;
import model.ICardResources;
import model.IGameIO;
import model.card.AbstractCard;
import model.card.CardType;

public class Kat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 1;
    
    Kat(ICardResources cardResources, IGameIO gameIO) {
        super(Card.KAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
        
    }
    
    public void complete() {
        // TODO Auto-generated method stub

    }

}
