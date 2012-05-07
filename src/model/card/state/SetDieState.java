package model.card.state;

import model.Die;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;

public class SetDieState extends CardState implements ICardState {

    private IDieChecker checker;
    
    public SetDieState(AbstractCard owner, IDieChecker checker) {
        super(owner);
        this.checker = checker;
    }

    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        Die die = handler.getDieInput();
        int amount = handler.getIntInput();
        
        if(checker.isValidDie(die)) {
            if(die.getValue() + amount >= 0 && die.getValue() + amount <= 6) {
                die.setValue(die.getValue() + amount);
                succeed = true;
                
                changeState();
            }
        }
        
        return succeed;
    }

}
