package model.card;

import model.ICardResources;
import model.IGameIO;
import model.IPlayer;
import model.card.state.TransferVpState;
import framework.cards.Card;

class TribunusPlebis extends AbstractCard {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    private static final int AMOUNT = 1;
    
    TribunusPlebis(ICardResources cardResources, IGameIO gameIO) {
        super(Card.TRIBUNUSPLEBIS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void activate() {
        
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        
        TransferVpState transfer = new TransferVpState(this, opponent, owner, AMOUNT);
        transfer.setNextState(null);
        
        setState(transfer);
    }

}
