package model.card.state;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class AttackSelectedTargetState extends AttackState implements ICardState {

    public AttackSelectedTargetState(AbstractCard owner, ICardChecker checker) {
        super(owner, checker);
    }

    @Override
    public AbstractCard getTarget() {
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        
        return disc.getCard();
        
    }
    
}
