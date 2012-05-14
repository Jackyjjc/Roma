package model.card.behaviour;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class SicariusBehaviour extends Behaviour {

    public SicariusBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (isValidTarget(target)) {
            target.disCard(true);
            //TODO: same as Nero
            getHost().disCard(true);
        }
    }

    public boolean isValidTarget(AbstractCard c) {

        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != getHost().getOwner()
                && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;

    }

}
