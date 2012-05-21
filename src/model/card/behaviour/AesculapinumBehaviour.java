package model.card.behaviour;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;
import model.card.IIntegerChecker;

/**
 * @author Chris Fong
 * @author Jacky CHEN
 */

public class AesculapinumBehaviour extends Behaviour implements ICardChecker, IIntegerChecker {

    private InputHandler handler;
    private ICardStorage discard;
    
    public AesculapinumBehaviour(AbstractCard host) {
        super(host);
        this.handler = getHost().getCardResources().getInputHandler();
        this.discard = getHost().getCardResources().getDiscardStorage();
    }

    @Override
    public void initialise() {
        handler.setList(discard);
    }

    public void complete() {
        ICardStorage hand = getOwner().getHand();
        AbstractCard card = handler.getCardInput();

        if (isValidCard(card)) {
            discard.removeCard(card);
            hand.pushCard(card);
        }

        handler.setList(hand);
    }

    public boolean isValidInt(int index) {

        boolean isValid = false;
        
        AbstractCard card = discard.getCard(index);

        if (discard.size() > index) {
            if (isValidCard(card)) {
                isValid = true;
            }
        }

        return isValid;
    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }

}
