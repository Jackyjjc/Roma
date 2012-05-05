package model.card.state;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class GetCardFromPileState extends CardState implements ICardState {

    private ICardChecker checker;
    private ICardStorage pile;
    
    public GetCardFromPileState(AbstractCard owner, ICardChecker checker, ICardStorage pile) {
        super(owner);
        this.checker = checker;
        this.pile = pile;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        ICardStorage hand = getOwner().getOwner().getHand();
        AbstractCard cardInput = null;
        
        int index = handler.getIntInput();
        
        if(index >= 0 && index < pile.size()) {
            cardInput = pile.getCard(index);
            pile.removeCard(cardInput);
        }
        
        if(checker.isValidCard(cardInput)) {
            hand.appendCard(cardInput);
            pile.shuffle();
            succeed = true;
            
            changeState();
        }
        
        return succeed;
    }

}
