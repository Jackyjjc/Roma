package model.card.state;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;

public class LayCardState extends CardState implements ICardState {

    private ChooseCardState state;
    
    public LayCardState(AbstractCard owner, ChooseCardState state) {
        
        super(owner);
        this.state = state;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        
        if(state.getChosenCard() != null && disc != null && !disc.isBlocked()) {
            
            state.getChosenCard().lay(disc);
            state.getChosenCard().setCost(state.getChosenCard().getDefaultCost());
            
            succeed = true;
            
            changeState();
        }
        
        return succeed;
    }

}
