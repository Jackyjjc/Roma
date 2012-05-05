package model.card.state;

import java.util.List;

import model.card.AbstractCard;

public class SetCardCostState implements ICardState {

    private List<AbstractCard> cards;
    private AbstractCard owner;
    private ICardState next;
    
    public SetCardCostState(List<AbstractCard> cards) {
        this.cards = cards;
    }
    
    public boolean run() {
        
        //hook
        
        return true;
    }
    
    public List<AbstractCard> getCards() {
        return cards;
    }
    
    public void setOwner(AbstractCard c) {
        this.owner = c;
    }

    public void setNextState(ICardState state) {
        this.next = state;
    }

    public ICardState getNextState() {
        return next;
    }
}
