package model.card.state;

import java.util.List;

import model.card.AbstractCard;

public class SetCardCostFreeState extends SetCardCostState implements ICardState {
    
    public SetCardCostFreeState(AbstractCard owner, List<AbstractCard> cards) {
        super(owner, cards);
    }
    
    public boolean run() {
        
        List<AbstractCard> cards = super.getCards();
        
        for(AbstractCard card : cards) {
            card.setCost(0);
        }
        
        changeState();
        
        return true;
    }
 
}
