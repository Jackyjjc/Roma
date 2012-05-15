package model.action;

import model.InputHandler;
import model.runner.CardActivateManager;
import framework.interfaces.GameState;

public class AddIntInputAction extends InputAction {

    public AddIntInputAction(GameState g, CardActivateManager manager,
                               InputHandler handler, int amount) {
        super(g, manager, handler);

        
    }

    public void run() {
        
    }

    @Override
    public boolean isValid() {
        // TODO Auto-generated method stub
        return false;
    }

}
