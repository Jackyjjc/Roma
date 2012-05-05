package model.card.state;

import model.card.AbstractCard;

public interface ICardState {

    public boolean run();
    
    public void setOwner(AbstractCard c);
    
    public void setNextState(ICardState state);
}
