package model.card.state;

import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IAmountChecker;

public class ChooseAmountState extends CardState implements ICardState {

    private IAmountChecker checker;
    private IResourceStorage from;
    private IResourceStorage to;
    
    private static final int TRADING_PRICE = 2;
    private static final int TRADING_REWARD = 1;
    
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
            
            TransferMoneyState transferMoney = new TransferMoneyState(getOwner(), to, from, amount * TRADING_PRICE);
            TransferVpState transferVP = new TransferVpState(getOwner(), from, to, amount * TRADING_REWARD);
            
            transferMoney.setNextState(transferVP);
            transferVP.setNextState(null);
            
            setNextState(transferMoney);
            succeed = true;
            
            changeState();
        }
        
        return succeed;
    }

}
