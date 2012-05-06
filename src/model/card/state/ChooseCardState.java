package model.card.state;

import java.util.List;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class ChooseCardState extends CardState implements ICardState {

    private AbstractCard cardChosen = null;
    private List<AbstractCard> cards;
    private ICardChecker checker;
    
    public ChooseCardState(AbstractCard owner, List<AbstractCard> cards, ICardChecker checker) {
        super(owner);
        this.cards = cards;
        this.checker = checker;
    }

    public boolean run() {

        boolean succeed = false;
        
        InputHandler handler = getOwner().getGameIO().getInputHandler();
        AbstractCard card = handler.getCardInput();
        
        if(checker.isValidCard(card)) {
            
            this.cardChosen = card;
            cards.remove(card);
            
            if(cards.isEmpty()) {
                setNextState(null);
            }
            
            changeState();
            
            succeed = true;
        }
        
        return succeed;
    }

    public AbstractCard getChosenCard() {
        return cardChosen;
    }
    
}
