package model.card.state;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AssassinState extends CardState implements ICardState {

    private ICardChecker checker;
    
    public AssassinState(AbstractCard owner, ICardChecker checker) {
        super(owner);
        this.checker = checker;
    }

    public boolean run() {
        
        boolean succeed = false;;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        AbstractCard target = handler.getCardInput();
        
        if(checker.isValidCard(target)) {
            target.disCard();
            getOwner().disCard();
            succeed = true;
            
            changeState();
        }
        
        return succeed;
    }
    
}
