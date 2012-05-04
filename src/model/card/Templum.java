package model.card;

import model.Die;
import model.ICardResources;
import model.IResourceStorage;
import model.Notifier;
import framework.cards.Card;
import framework.interfaces.activators.TemplumActivator;

class Templum extends AbstractCard implements IForumListener, TemplumActivator {

    private static final int COST = 2;
    private static final int DEFENCE = 2;
    
    Templum(ICardResources cardResources, Notifier notifier) {
        
        super(Card.TEMPLUM, CardType.BUILDING,
              COST, DEFENCE, cardResources, notifier);
        
    }

    public void activate() {
        
    }
    
    public void complete() {
        // TODO Auto-generated method stub
        
    }

    public void notifyForumActivate(Die actiondie) {
        
        IResourceStorage bank = getCardResources().getBank();
        
        Action.attainVP(bank, this.getOwner(), actiondie.getValue());
    }
}
