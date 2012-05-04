package model.card;

import model.ICardResources;
import model.IPlayer;
import model.IResourceStorage;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.LegatActivator;

class Legat extends AbstractCard implements LegatActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Legat(ICardResources cardResources, Notifier notifier) {
        
        super(Card.LEGAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
    }

    public void activate() {
        
        IResourceStorage bank = getCardResources().getBank();
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        Action.attainVP(bank, owner, amount);

    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }

}
