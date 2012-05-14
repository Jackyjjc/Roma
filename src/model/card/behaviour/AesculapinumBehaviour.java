package model.card.behaviour;

import model.ICardStorage;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AesculapinumBehaviour extends Behaviour {

    public AesculapinumBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();
        ICardStorage hand = getHost().getOwner().getHand();
        ICardStorage discard = getHost().getCardResources().getDiscardStorage();

        int index = handler.getIntInput();
        AbstractCard card = discard.getCard(index);

        if (isValidCard(card)) {
            discard.removeCard(card);
            hand.pushCard(card);
        }
    }

    private boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }

}
