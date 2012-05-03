package model.card;

import model.ICardStorage;
import model.IListener;
import model.IResourceStorage;
import model.Notifier;

class Basilica extends AbstractCard implements IListener {

    private static final int COST = 6;
    private static final int DEFENCE = 5;
    private static final int ADDTIONAL_VP = 2;
    
    private IResourceStorage bank;
    
    Basilica(IResourceStorage bank, ICardStorage grave, Notifier notifier) {
        super(Card.BASILICA, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
     
        this.bank = bank;
    }

    public void activate() {

    }

    public void update() {
        
        Action.attainVP(bank, this.getOwner(), ADDTIONAL_VP);
    }
    
}
