package model.action;

import framework.interfaces.GameState;
import model.InputHandler;
import model.runner.CardActivateManager;

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
