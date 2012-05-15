package model.card.behaviour;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnagerBehaviour extends Behaviour {


    public OnagerBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();

        AbstractCard target = handler.getDiscInput().getCard();

        if(isValidTarget(target)) {
            int value = handler.getBattleDieInput();
            Action.attack(target, value);
        }
    }

    private boolean isValidTarget(AbstractCard target) {
        
        boolean isValid = false;
        if(target != null && target.getOwner() != null 
                && target.getOwner() != getHost().getOwner()
                && target.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }

}
