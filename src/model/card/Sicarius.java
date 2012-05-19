package model.card;

import model.ICardResources;
import model.card.behaviour.SicariusBehaviour;
import framework.cards.Card;

public class Sicarius extends AbstractCard {

    private static final int COST = 9;
    private static final int DEFENCE = 2;
    
    Sicarius(ICardResources cardResources) {
        
        super(Card.SICARIUS, 
              CardType.CHARACTER,
              COST, 
              DEFENCE, 
              cardResources);
        
    }
    
    static AbstractCard create(ICardResources cardResources) {
        
        AbstractCard card = new Sicarius(cardResources);
        card.setBehaviour(new SicariusBehaviour(card));
        
        return card;
    }
}
