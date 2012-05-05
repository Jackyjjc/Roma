package model.card.state;

import model.Die;
import model.IResourceStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.IDieChecker;

public class UseDieGetVpState extends CardState implements ICardState {

    private IResourceStorage from;
    private IResourceStorage to;
    private IDieChecker checker;
    
    public UseDieGetVpState(AbstractCard owner, IDieChecker checker, 
                            IResourceStorage from, IResourceStorage to) {
        super(owner);
        this.checker = checker;
        this.from = from;
        this.to = to;
    }

    public boolean run() {

        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        Die die = handler.getDieInput();
        
        if(checker.isValidDie(die)) {
            
            TransferVpState transferVP = new TransferVpState(getOwner(), from, to, die.getValue());
            if(transferVP.run()) {
                die.use();
            }
            
            changeState();
        }
        
        return succeed;
    }

}
