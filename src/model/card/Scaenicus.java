package model.card;

import model.ICardResources;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.CardActivator;
import framework.interfaces.activators.ScaenicusActivator;

class Scaenicus extends AbstractCard implements ScaenicusActivator {

    private static final int COST = 8;
    private static final int DEFENCE = 3;
    
    private CardFactory factory;
    
    Scaenicus(ICardResources cardResources, CardFactory factory, Notifier notifier) {
        super(Card.SCAENICUS, CardType.CHARACTER,
              COST, DEFENCE, cardResources, notifier);
        
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
