package model.card.behaviour;

import model.ICardStorage;
import model.IField;
import model.card.AbstractCard;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsiliariusBehaviour extends Behaviour {

    private ICardStorage charCards;

    public ConsiliariusBehaviour(AbstractCard host) {
        super(host);

    }

    public void initialise() {

        IField discs = getHost().getOwner().getField();
        this.charCards = discs.removeCardsOf(CardType.CHARACTER);

        for (AbstractCard c : charCards) {
            c.setCost(0);
        }

        getHost().getGameIO().getInputHandler().setList(charCards);

    }

    public void complete() {

        for (AbstractCard c : charCards) {
            c.setCost(c.getDefaultCost());
        }

        getHost().getGameIO().getInputHandler().setList(getHost().getOwner().getHand());
    }

    public boolean isValidCard (AbstractCard c) {

        boolean isValid = false;

        if(c != null && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;

    }

}
