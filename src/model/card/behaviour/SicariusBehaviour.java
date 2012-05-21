package model.card.behaviour;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.ICardChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class SicariusBehaviour extends Behaviour implements ICardChecker {

    public SicariusBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getCardResources().getInputHandler();
        IDisc disc = handler.getDiscInput();
        AbstractCard target = disc.getCard();

        if (target != null && isValidCard(target)) {
            target.disCard();
        }
        
        getHost().disCard();
    }

    public boolean isValidCard(AbstractCard c) {

        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != getHost().getOwner()) {
            isValid = true;
        }

        return isValid;

    }

}
