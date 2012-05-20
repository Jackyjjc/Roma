package model.action;

import model.IDisc;
import model.InputHandler;
import model.card.ICardChecker;
import model.runner.CardActivateManager;
import framework.interfaces.GameState;

public class MimicAction extends InputAction {

    private int diceDisc;
    
    public MimicAction(GameState g, CardActivateManager manager, InputHandler handler, int diceDisc) {
        super(g, manager, handler);
    }

    public void run() {
        getCardActivateManager().getScaenicusMimicTarget(diceDisc);
    }

    public boolean isValid() {

        boolean isValid = false;
        
        getInputHandler().addDiscInput(getGameState().getWhoseTurn(), diceDisc);
        IDisc disc = getInputHandler().getDiscInput();
        ICardChecker checker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        
        if(!disc.isEmpty() && checker.isValidCard(disc.getCard())) {
            isValid = true;
        }
        
        
        return isValid;
    }

}
