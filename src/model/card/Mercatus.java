package model.card;

import model.ICardResources;
import model.IField;
import model.IGameIO;
import model.IPlayer;
import model.card.state.TransferVpState;
import framework.cards.Card;

class Mercatus extends AbstractCard {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    Mercatus(ICardResources cardResources, IGameIO gameIO) {
        super(Card.MERCATUS, CardType.BUILDING,
              COST, DEFENCE, cardResources, gameIO);
    }

    public void activate() {
        
        IPlayer opponent = this.getOwner().getOpponent();
        IField discs = opponent.getField();
        AbstractCard c = null;
        
        int numOfForum = 0;
        
        for(int i = 0; i < discs.getNumDiscs(); i++) {
            c = discs.getCard(i);
            if(c != null && c instanceof Forum) {
                numOfForum++;
            }
        }
        
        new TransferVpState(this, opponent, this.getOwner(), numOfForum).run();
    }
    
}
