package model.action;

import model.InputHandler;
import model.runner.CardActivateManager;
import framework.interfaces.GameState;

public class CompleteAction extends InputAction {

    public CompleteAction(GameState g, CardActivateManager manager,
            InputHandler handler) {
        super(g, manager, handler);
        
    }

    public void run() {
        getCardActivateManager().complete();
    }

    public boolean isValid() {
        return true;
    }
}
