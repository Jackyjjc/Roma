package model.card.state;

import model.InputHandler;
import model.card.AbstractCard;

public class DecisionState extends CardState implements ICardState {

    private ICardState trueState;
    private ICardState falseState;
    
    public DecisionState(AbstractCard owner, 
                         ICardState trueState, ICardState falseState) {
        super(owner);
        this.trueState = trueState;
        this.falseState = falseState;
    }

    public boolean run() {

        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        boolean boolInput = handler.getBooleanInput();
        
        if(boolInput == true) {
            setNextState(trueState);
        } else {
            setNextState(falseState);
        }
        
        changeState();
        
        return true;
    }

}
