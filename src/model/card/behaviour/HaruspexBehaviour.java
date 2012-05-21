package model.card.behaviour;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

public class HaruspexBehaviour extends Behaviour implements ICardChecker {

    public HaruspexBehaviour(AbstractCard host) {
        super(host);
    }

    @Override
    public void initialise() {
        InputHandler handler = getHost().getCardResources().getInputHandler();
        ICardStorage deck = getHost().getCardResources().getDeckStorage();
        handler.setList(deck);
    }

    public void complete() {

        InputHandler handler = getHost().getCardResources().getInputHandler();
        ICardStorage hand = getHost().getOwner().getHand();
        ICardStorage deck = getHost().getCardResources().getDeckStorage();

        AbstractCard card = handler.getCardInput();

        if (isValidCard(card)) {
            deck.removeCard(card);
            deck.shuffle();
            hand.pushCard(card);
        }

        handler.setList(hand);
    }

    public boolean isValidCard(AbstractCard card) {

        boolean isValid = false;

        if (card != null) {
            isValid = true;
        }

        return isValid;
    }
}
