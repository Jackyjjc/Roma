package model.card;

import model.ICardStorage;
import model.Notifier;

class Scaenicus extends AbstractCard implements ScaenicusActivator {

    private static final int COST = 8;
    private static final int DEFENCE = 3;
    
    private CardFactory factory;
    
    Scaenicus(ICardStorage grave, CardFactory factory, Notifier notifier) {
        super(Card.SCAENICUS, CardType.CHARACTER,
              COST, DEFENCE, grave, notifier);
        
        this.factory = factory;
    }

    public void activate() {
        
    }
    
    public boolean isValidTarget(AbstractCard c) {
        
        boolean isValid = false;
        
        if(c.getType() == CardType.CHARACTER 
           && c.getOwner() == this.getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }

    public void complete() {
        // TODO Auto-generated method stub
        
    }

    public CardActivator getScaenicusMimicTarget(int diceDisc) {

        AbstractCard target = getOwner().getField().getCard(diceDisc);
        
        AbstractCard clone = factory.create(target.getName());;
        
        clone.setDisc(this.getDisc());
        clone.activate();
        
        return (CardActivator) clone;
        
    }
}
