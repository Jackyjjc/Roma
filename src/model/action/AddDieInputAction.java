package model.action;

import framework.interfaces.GameState;
import model.Die;
import model.InputHandler;
import model.card.IDieChecker;
import model.runner.CardActivateManager;

public class AddDieInputAction extends InputAction {

    private int dieValue;

    public AddDieInputAction(GameState g, CardActivateManager manager,
                             InputHandler handler, int dieValue) {
        super(g, manager, handler);

        this.dieValue = dieValue;
    }

    public void run() {
        getInputHandler().addDieInput(dieValue);
    }

    public boolean isValid() {

        boolean isValid = false;

        IDieChecker checker = (IDieChecker) getCardActivateManager().getActivatedCard().getBehaviour();

        getInputHandler().addDieInput(dieValue);
        Die die = getInputHandler().getDieInput();

        if (checker.isValidDie(die)) {
            isValid = true;
        }

        return isValid;
    }

}
