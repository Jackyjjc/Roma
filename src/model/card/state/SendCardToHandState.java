package model.card.state;

import model.ICardStorage;
import model.IDisc;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class SendCardToHandState extends CardState implements ICardState {

    private ICardChecker checker;
    
    public SendCardToHandState(AbstractCard owner, ICardChecker checker) {
        super(owner);
        this.checker = checker;
    }

    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        IPlayer opponent = getOwner().getOwner().getOpponent();
        ICardStorage opponentHand = opponent.getHand();
        
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();
        
        if(checker.isValidCard(target)) {
            disc.removeCard();
            opponentHand = target.getOwner().getHand();
            opponentHand.appendCard(target);
            succeed = true;
            
            changeState();
        }
    
        return succeed;
    }

}
