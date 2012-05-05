package model.card.state;

import java.util.List;

import model.card.AbstractCard;

public class SetCardCostFreeState extends SetCardCostState implements ICardState {
    
    public SetCardCostFreeState(List<AbstractCard> cards) {
        super(cards);
    }
    
    @Override
    public boolean run() {
        
        List<AbstractCard> cards = super.getCards();
        
        for(AbstractCard card : cards) {
            card.setCost(card.getDefaultCost());
        }
        
        setNextState(super.getNextState());
        
        return true;
    }
 
}
