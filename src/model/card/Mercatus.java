package model.card;

import model.ICardStorage;
import model.IField;
import model.IPlayer;
import model.Notifier;

class Mercatus extends AbstractCard implements MercatusActivator {

    private static final int COST = 6;
    private static final int DEFENCE = 3;
    
    Mercatus(ICardStorage grave, Notifier notifier) {
        super(Card.MERCATUS, CardType.BUILDING,
              COST, DEFENCE, grave, notifier);
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
        
        Action.attainVP(opponent, this.getOwner(), numOfForum);

    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }
    
}
