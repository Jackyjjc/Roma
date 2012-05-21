package model.card.behaviour;

import model.ICardResources;
import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class RetrieveCardFromPileBehaviour extends Behaviour {

    private InputHandler handler;
    private ICardStorage pile;
    private ICardChecker checker;
    
    public RetrieveCardFromPileBehaviour(AbstractCard host, ICardResources cardResources, 
                                         ICardStorage pile, ICardChecker cardChecker) {
        super(host, cardResources);
        this.pile = pile;
        this.handler = getCardResources().getInputHandler();
        this.checker = cardChecker;
    }

    @Override
    public void initialise() {
        handler.setList(pile);
    }
    
    public void complete() {
        ICardStorage hand = getHost().getOwner().getHand();
        AbstractCard card = handler.getCardInput();

        if (checker.isValidCard(card)) {
            pile.removeCard(card);
            hand.pushCard(card);
        }

        handler.setList(hand);
    }

}
