package model.action;

import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

public class EndTurnAction extends MoveMakingAction {

    public EndTurnAction(GameState g, MoveMaker moveMaker) {
        super(g, moveMaker);
    }

    public void run() {
        getMoveMaker().endTurn();
    }

    public boolean isValid() {

        boolean isValid = false;

        if (!getGameState().isGameCompleted()) {
            isValid = true;
        }

        return isValid;
    }

}
