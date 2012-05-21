package model.card.behaviour;

import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;
import model.card.ICardChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnagerBehaviour extends Behaviour implements ICardChecker {


    public OnagerBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        InputHandler handler = getHost().getCardResources().getInputHandler();

        AbstractCard target = handler.getDiscInput().getCard();

        if (target != null && isValidCard(target)) {
            int value = handler.getBattleDieInput();
            Action.attack(target, value);
        }
    }

    public boolean isValidCard(AbstractCard target) {

        boolean isValid = false;
        if (target != null && target.getOwner() != null
                && target.getOwner() != getHost().getOwner()
                && target.getType() == CardType.BUILDING) {
            isValid = true;
        }

        return isValid;
    }

}
