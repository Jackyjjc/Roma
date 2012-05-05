package model.card.state;

import model.IResourceStorage;
import model.card.AbstractCard;

public class TransferVpState extends CardState implements ICardState {

    private IResourceStorage from;
    private IResourceStorage to;
    private int amount;
    
    public TransferVpState(AbstractCard owner, 
                           IResourceStorage from, IResourceStorage to, int amount) {
        super(owner);
    }

    public boolean run() {
        
        boolean succeed = false;
        
        if(isValidAmount()) {
            from.transferVP(to, amount);
            changeState();
        }
        
        return succeed;
    }

    private boolean isValidAmount() {
        
        boolean isValid = false;
        
        if(from != null && to != null && from.getVP() >= amount) {
            isValid = true;
        }
        
        return isValid;
    }
    
}
