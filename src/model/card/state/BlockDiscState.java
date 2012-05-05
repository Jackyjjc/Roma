package model.card.state;

import java.util.List;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;

public class BlockDiscState extends CardState implements ICardState {

    List<IDisc> affectedDiscs;
    
    public BlockDiscState(AbstractCard owner, List<IDisc> affectedDiscs) {
        
        super(owner);
        this.affectedDiscs = affectedDiscs;
    }

    public boolean run() {

        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        
        IDisc disc = handler.getDiscInput();
        
        disc.block();
        affectedDiscs.add(disc);
        
        changeState();
        
        return succeed;
    }

}
