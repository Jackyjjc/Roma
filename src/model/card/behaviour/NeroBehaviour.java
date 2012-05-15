package model.card.behaviour;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class NeroBehaviour extends Behaviour {

    public NeroBehaviour(AbstractCard host) {
		super(host);
	}

	public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (isValidTarget(target)) {
            target.disCard();
        }

        this.disCard();
    }

    public boolean isValidTarget(AbstractCard c) {
        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != getHost().getOwner()
                && c.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }
}
