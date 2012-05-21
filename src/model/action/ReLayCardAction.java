package model.action;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.runner.CardActivateManager;
import framework.cards.Card;
import framework.interfaces.GameState;

public class ReLayCardAction extends InputAction {

    private Card name;
    private int diceDisc;
    
    public ReLayCardAction(GameState g, CardActivateManager manager,
                           InputHandler handler, Card name, int diceDisc) {
        super(g, manager, handler);
        this.name = name;
        this.diceDisc = diceDisc;
    }

    public void run() {
        getCardActivateManager().placeCard(name, diceDisc);
    }

    public boolean isValid() {
        
        boolean isValid = false;
        
        ICardChecker checker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        getInputHandler().addCardInput(getGameState().getWhoseTurn(), name);
        AbstractCard card = getInputHandler().getCardInput();
        
        if(checker.isValidCard(card)) {
            isValid = true;
        }
        
        return isValid;
    }
}