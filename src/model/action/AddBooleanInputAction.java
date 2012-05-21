package model.action;

import framework.interfaces.GameState;
import model.InputHandler;
import model.runner.CardActivateManager;

public class AddBooleanInputAction extends InputAction {

    private boolean input;

    public AddBooleanInputAction(GameState g, CardActivateManager manager,
                                 InputHandler handler, boolean input) {

        super(g, manager, handler);

        this.input = input;
    }

    public void run() {
        getInputHandler().addBooleanInput(input);
    }

    public boolean isValid() {
        return true;
    }
}
