package model.card.behaviour;

import model.ICardStorage;
import model.IField;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;
import model.card.ICardChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsiliariusBehaviour extends Behaviour implements ICardChecker {

    private ICardStorage charCards;

    public ConsiliariusBehaviour(AbstractCard host) {
        super(host);
    }

    public void initialise() {

        IField discs = getHost().getOwner().getField();
        this.charCards = discs.getCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }

        getHost().getCardResources().getInputHandler().setList(charCards);

    }

    public void complete() {

        ICardStorage hand = getHost().getOwner().getHand();

        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }

        InputHandler handler = getHost().getCardResources().getInputHandler();
        handler.setList(hand);
    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if (c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;

    }

}
