package model.card.state;

import java.util.List;

import model.card.AbstractCard;

public class SetCardCostDefaultState extends SetCardCostState implements ICardState {
    
    public SetCardCostDefaultState(List<AbstractCard> cards) {
        super(cards);
    }
    
    @Override
    public boolean run() {
        
        List<AbstractCard> cards = super.getCards();
        
        for(AbstractCard card : cards) {
            card.setCost(0);
        }
        
        setNextState(super.getNextState());
        
        return true;
    }
 
}
