package model.action;

import model.InputHandler;
import model.runner.CardActivateManager;
import framework.interfaces.GameState;

public class AddBattleDieInput extends InputAction {

    private int roll;
    
    public AddBattleDieInput(GameState g, CardActivateManager manager,
                             InputHandler handler, int roll) {
        
        super(g, manager, handler);
        this.roll = roll;
    }

    public void run() {
        getInputHandler().addBattleDieInput(roll);
    }

    public boolean isValid() {
        return true;
    }

}
