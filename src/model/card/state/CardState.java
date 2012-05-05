package model.card.state;

import model.card.AbstractCard;

public abstract class CardState {
    
    private AbstractCard owner;
    private ICardState next;
    
    public abstract boolean run();
    
    public CardState(AbstractCard owner) {
        this.owner = owner;
    }
    
    public void setOwner(AbstractCard c) {
        this.owner = c;
    }
    
    public AbstractCard getOwner() {
        return owner;
    }
    
    public void setNextState(ICardState state) {
        this.next = state;
    }
    
    public ICardState getNextState() {
        return next;
    }
    
    public void changeState() {
        
        if(getNextState() != null) {
            getNextState().setOwner(getOwner());
        }
        
        getOwner().setState(getNextState());
    }
}
