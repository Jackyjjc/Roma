package model.card.state;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.card.IDieChecker;

public class CenturioRollDieState extends CardState implements ICardState {

    private ICardChecker cardChecker;
    private IDieChecker dieChecker;
    
    public CenturioRollDieState(AbstractCard owner) {
        super(owner);
        cardChecker = (ICardChecker)getOwner();
        dieChecker = (IDieChecker)getOwner();
    }

    public boolean run() {
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();

        int dieValue = handler.getBattleDieInput();
        
        CenturioAttackState trueState = new CenturioAttackState(getOwner(),cardChecker,dieChecker,dieValue, true);
        
        CenturioAttackState falseState = new CenturioAttackState(getOwner(),cardChecker,dieChecker,dieValue, false);
        
        setNextState(new DecisionState(getOwner(), trueState, falseState));
        
        changeState();
        
        return true;
    
    }

}
