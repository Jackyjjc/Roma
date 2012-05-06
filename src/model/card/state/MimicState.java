package model.card.state;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardFactory;
import model.card.ICardChecker;

public class MimicState extends CardState implements ICardState {

    private ICardChecker checker;
    private CardFactory factory;
    
    public MimicState(AbstractCard owner, ICardChecker checker, CardFactory factory) {
        super(owner);
        this.checker = checker;
        this.factory = factory;
    }

    public boolean run() {

        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        
        if(checker.isValidCard(target)) {
            AbstractCard mimic = factory.create(target.getName());
            mimic.setOwner(getOwner().getOwner());
            mimic.setDisc(getOwner().getDisc());
            mimic.activate();
            succeed = true;
            
            setNextState(mimic.getState());
            changeState();
        }
        
        return succeed;
    }

}
