package model.card;

import model.ICardStorage;
import model.IPlayer;
import model.Notifier;

class TribunusPlebis extends AbstractCard implements TribunusPlebisActivator {

    private static final int COST = 5;
    private static final int DEFENCE = 5;
    private static final int AMOUNT = 1;
    
    TribunusPlebis(ICardStorage grave, Notifier notifier) {
        super(Card.TRIBUNUSPLEBIS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
    }

    public void activate() {
        
        IPlayer owner = this.getOwner();
        IPlayer opponent = owner.getOpponent();
        
        Action.attainVP(opponent, owner, AMOUNT);

    }
    
    public boolean isValid() {
        
        boolean isValid = false;
        
        if (this.getOwner().getOpponent().getVP() >= AMOUNT) {
            isValid = true;
        }
        
        return isValid;
        
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }


}
