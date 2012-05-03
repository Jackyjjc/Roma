package model.card;

import model.ICardStorage;
import model.IPlayer;
import model.IResourceStorage;
import model.Notifier;

class Legat extends AbstractCard implements LegatActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    private IResourceStorage bank;
    
    Legat(IResourceStorage bank, ICardStorage grave, Notifier notifier) {
        
        super(Card.LEGAT, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
    }

    public void activate() {
        
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        Action.attainVP(bank, owner, amount);

    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }

}
