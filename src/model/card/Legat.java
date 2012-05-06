package model.card;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.IResourceStorage;
import model.card.state.TransferVpState;
import framework.cards.Card;

class Legat extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 2;
    
    Legat(ICardResources cardResources, IGameIO gameIO) {
        
        super(Card.LEGAT, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void activate() {
        
        IResourceStorage bank = getCardResources().getBank();
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        int amount = opponent.getField().countUnoccupiedDiscs();

        TransferVpState transferVP = new TransferVpState(this, bank, owner, amount);
        transferVP.setNextState(null);
        
        setState(transferVP);
        runState();
    }

}
