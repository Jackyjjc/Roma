package model.action;

import model.InputHandler;
import model.card.IIntegerChecker;
import model.runner.CardActivateManager;
import framework.interfaces.GameState;

public class AddIntInputAction extends InputAction {

    private int amount;
    
    public AddIntInputAction(GameState g, CardActivateManager manager,
                               InputHandler handler, int amount) {
        super(g, manager, handler);

        this.amount = amount;
    }

    public void run() {
        getInputHandler().addIntInput(amount);
    }

    public boolean isValid() {
        
        boolean isValid = false;
        
        IIntegerChecker checker = (IIntegerChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        
        if(checker.isValidInt(amount)) {
            isValid = true;
        }
        
        return isValid;
    }

}
