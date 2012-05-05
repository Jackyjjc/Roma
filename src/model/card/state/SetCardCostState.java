package model.card.state;

import java.util.List;

import model.card.AbstractCard;

public class SetCardCostState extends CardState implements ICardState {

    private List<AbstractCard> cards;
    
    public SetCardCostState(AbstractCard owner, List<AbstractCard> cards) {
        super(owner);
        this.cards = cards;
    }
    
    public boolean run() {
        
        //hook
        
        changeState();
        
        return true;
    }
    
    public List<AbstractCard> getCards() {
        return cards;
    }
    
}
