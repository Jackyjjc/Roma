package model.card.state;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AttackState extends CardState implements ICardState {

    private ICardChecker checker;
    
    public AttackState(AbstractCard owner, ICardChecker checker) {
        super(owner);
        this.checker = checker;
    }

    public boolean run() {
        
        boolean succeed = false;
        
        AbstractCard target = getTarget();
        
        if(checker.isValidCard(target)) {
            if(target.getDefence() <= getDieValue()) {
                target.disCard(); 
            }
            succeed = true;
            changeState();
        }
        
        return succeed;
    }
    
    public AbstractCard getTarget() {
        
        //A hook
        
        return null;
    }
    
    public int getDieValue() {
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        return handler.getBattleDieInput();
    }

}
