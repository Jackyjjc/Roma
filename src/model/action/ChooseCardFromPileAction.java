package model.action;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.runner.CardActivateManager;
import framework.cards.Card;
import framework.interfaces.GameState;

public class ChooseCardFromPileAction extends InputAction {
    
    private Card name;
    
    public ChooseCardFromPileAction(GameState g, CardActivateManager manager,
                                      InputHandler handler, Card name) {
        
        super(g, manager, handler);
        this.name = name;
    }

    public void run() {
        getCardActivateManager().chooseCardFromPile(name);
    }

    public boolean isValid() {
        
        boolean isValid = false;
        
        ICardChecker cardChecker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        getCardActivateManager().chooseCardFromPile(name);
        AbstractCard card = getInputHandler().getCardInput();
        
        if(cardChecker.isValidCard(card) || card.getName() == name) {
            isValid = true;
        }
        
        return isValid;
        
    }

}
