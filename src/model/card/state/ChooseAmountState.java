package model.card.state;

import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IAmountChecker;

public class ChooseAmountState extends CardState implements ICardState {

    private IAmountChecker checker;
    private IResourceStorage from;
    private IResourceStorage to;
    
    public ChooseAmountState(AbstractCard owner, IAmountChecker checker, 
                             IResourceStorage from, IResourceStorage to) {
        super(owner);
        
        this.checker = checker;
        this.from = from;
        this.to = to;
    }

    public boolean run() {

        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        int amount = handler.getIntInput();
    
        if(checker.isValidAmount(amount)) {
            setNextState(new TransferVpState(getOwner(), from, to, amount));
            succeed = true;
            
            changeState();
        }
        
        return succeed;
    }

}
