package model.card.state;

import model.IResourceStorage;
import model.card.AbstractCard;

public class TransferMoneyState extends CardState implements ICardState {

    private IResourceStorage from;
    private IResourceStorage to;
    private int amount;
    
    public TransferMoneyState(AbstractCard owner, 
                           IResourceStorage from, IResourceStorage to, int amount) {
        super(owner);
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public boolean run() {
        
        boolean succeed = false;
        
        if(isValidAmount()) {
            from.transferMoney(to, amount);
            changeState();
        }
        
        return succeed;
    }

    private boolean isValidAmount() {
        
        boolean isValid = false;
        
        if(from != null && to != null && from.getMoney() >= amount) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
