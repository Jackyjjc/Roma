package model.card.state;

import java.util.*;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;

public class LayCardState extends CardState implements ICardState {

    private ChooseCardState state;
    private List<AbstractCard> cards;
    
    public LayCardState(AbstractCard owner, ChooseCardState state, List<AbstractCard> floatingCards) {
        
        super(owner);
        this.state = state;
        this.cards = floatingCards;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        
        if(state.getChosenCard() != null && disc != null && !disc.isBlocked()) {
            
            state.getChosenCard().lay(disc);
            state.getChosenCard().setCost(state.getChosenCard().getDefaultCost());
            
            succeed = true;
            
            if(cards.isEmpty()) {
            	setNextState(null);
            }
            
            changeState();
        }
        
        return succeed;
    }

}
