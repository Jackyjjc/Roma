package model.card;

import model.ICardResources;
import model.IListener;
import model.IResourceStorage;
import model.Notifier;
import framework.cards.Card;

class Basilica extends AbstractCard implements IListener {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    private static final int ADDTIONAL_VP = 2;
    
    Basilica(ICardResources cardResources, Notifier notifier) {
        super(Card.BASILICA, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {

    }

    public void update() {
        
        IResourceStorage bank = getCardResources().getBank();
        
        Action.attainVP(bank, getOwner(), ADDTIONAL_VP);
    }
    
}
