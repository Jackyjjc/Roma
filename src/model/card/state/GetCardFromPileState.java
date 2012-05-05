package model.card.state;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class GetCardFromPileState implements ICardState {

    private AbstractCard owner;
    private ICardChecker checker;
    private ICardState next;
    
    public GetCardFromPileState(AbstractCard card, ICardChecker checker) {
        this.owner = card;
        this.checker = checker;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = owner.getGameIO().getInputHandler();
        ICardStorage hand = owner.getOwner().getHand();
        AbstractCard cardInput = handler.getCardInput();
        
        if(checker.isValidCard(cardInput)) {
            hand.appendCard(cardInput);
            succeed = true;
        }
        
        owner.setState(next);
        
        return succeed;
    }

    public void setOwner(AbstractCard c) {
        this.owner = c;
    }

    public void setNextState(ICardState state) {
        this.next = state;
    }
}
