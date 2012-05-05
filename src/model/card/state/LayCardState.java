package model.card.state;

import java.util.List;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class LayCardState extends CardState implements ICardState {

    private List<AbstractCard> cards;
    private ICardChecker checker;
    
    public LayCardState(AbstractCard owner, List<AbstractCard> cards, ICardChecker checker) {
        
        super(owner);
        this.cards = cards;
        this.checker = checker;
    }
    
    public boolean run() {
        
        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard card = handler.getCardInput();
        
        if(checker.isValidCard(card) && disc != null && !disc.isBlocked()) {
            
            card.lay(disc);
            card.setCost(card.getDefaultCost());
            cards.remove(card);
            
            succeed = true;
            
            if(!cards.isEmpty()) {
                setNextState(this);
            }
            
            changeState();
        }
        
        return succeed;
    }

}
