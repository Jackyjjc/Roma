package model.action;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;
import model.card.IIntegerChecker;
import model.runner.CardActivateManager;
import framework.cards.Card;
import framework.interfaces.GameState;

public class ChooseCardFromPileAction extends InputAction {
    
    private Card name;
    private int index;
    
    public ChooseCardFromPileAction(GameState g, CardActivateManager manager,
                                      InputHandler handler, Card name, int index) {
        
        super(g, manager, handler);
        this.name = name;
        this.index = index;
    }

    public void run() {
        getCardActivateManager().chooseCardFromPile(index);
    }

    public boolean isValid() {
        
        boolean isValid = false;
        
        ICardChecker cardChecker = (ICardChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        IIntegerChecker intChecker = (IIntegerChecker) getCardActivateManager().getActivatedCard().getBehaviour();
        
        if(intChecker.isValidInt(index)) {
            
            getCardActivateManager().chooseCardFromPile(index);
            AbstractCard card = getInputHandler().getCardInput();
            
            if(cardChecker.isValidCard(card) && card.getName() == name) {
                isValid = true;
            }
            
        }
        
        return isValid;
        
    }

}
