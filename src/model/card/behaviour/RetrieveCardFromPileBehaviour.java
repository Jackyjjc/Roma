package model.card.behaviour;

import model.ICardResources;
import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

public class RetrieveCardFromPileBehaviour extends Behaviour implements ICardChecker {

    private InputHandler handler;
    private ICardStorage pile;
    private CardType checkType;
    
    public RetrieveCardFromPileBehaviour(AbstractCard host, ICardResources cardResources, 
                                         ICardStorage pile, CardType checkType) {
        super(host, cardResources);
        this.pile = pile;
        this.handler = getCardResources().getInputHandler();
    }

    @Override
    public void initialise() {
        handler.setList(pile);
    }
    
    public void complete() {
        ICardStorage hand = getHost().getOwner().getHand();
        AbstractCard card = handler.getCardInput();

        if (isValidCard(card)) {
            pile.removeCard(card);
            if(checkType == null) {
                pile.shuffle();
            }
            hand.pushCard(card);
        }

        handler.setList(hand);
    }
    
    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null 
            && (checkType == null || c.getType() == checkType)) {
            isValid = true;
        }

        return isValid;
    }
}
