package model.action;

import framework.interfaces.GameState;
import model.InputHandler;
import model.runner.CardActivateManager;

public abstract class InputAction extends Action {

    private CardActivateManager manager;
    private InputHandler handler;

    public InputAction(GameState g, CardActivateManager manager, InputHandler handler) {
        super(g);
        this.manager = manager;
        this.handler = handler;
    }

    public CardActivateManager getCardActivateManager() {
        return manager;
    }

    public InputHandler getInputHandler() {
        return handler;
    }

}
