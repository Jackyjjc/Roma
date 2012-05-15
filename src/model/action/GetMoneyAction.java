package model.action;

import framework.interfaces.GameState;
import framework.interfaces.MoveMaker;

public class GetMoneyAction extends MoveMakingAction {

    private int diceToUse;
    
    public GetMoneyAction(GameState g, MoveMaker moveMaker,
                          int diceToUse) {
        super(g, moveMaker);
        this.diceToUse = diceToUse;
    }

    public void run() {
        getMoveMaker().activateMoneyDisc(diceToUse);
    }

    public boolean isValid() {
        //you can always get money;
        return true;
    }

}
