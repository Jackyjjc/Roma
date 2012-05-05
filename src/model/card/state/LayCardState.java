package model.card.state;

import java.util.List;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class LayCardState implements ICardState {

    private AbstractCard owner;
    private List<AbstractCard> cards;
    private ICardChecker checker;
    private ICardState next;
    
    
    public LayCardState(AbstractCard owner, List<AbstractCard> cards, ICardChecker checker) {
        
        this.owner = owner;
        this.cards = cards;
        this.checker = checker;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = owner.getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard card = handler.getCardInput();
        
        if(checker.isValidCard(card) && !disc.isBlocked()) {
            
            card.lay(disc);
            card.setCost(card.getDefaultCost());
            cards.remove(card);
            
            succeed = true;
        }
        
        if(!cards.isEmpty()) {
            owner.setState(this);
        } else {
            owner.setState(next);
        }
        
        return succeed;
    }

    public void setOwner(AbstractCard c) {
        this.owner = c;
    }

    public void setNextState(ICardState state) {
        this.next = state;
    }

}
