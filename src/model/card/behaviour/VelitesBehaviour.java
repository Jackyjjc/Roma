package model.card.behaviour;

import model.IDisc;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.CardType;
import model.card.ICardChecker;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class VelitesBehaviour extends Behaviour implements ICardChecker{

    public VelitesBehaviour (AbstractCard host) {
        super(host);
    }

    public void initialise() {

    }

    public void complete() {

        InputHandler handler = getHost().getGameIO().getInputHandler();

        IDisc targetDisc = handler.getDiscInput();

        if (targetDisc != null) {

            AbstractCard targetCard = targetDisc.getCard();

            if(targetCard != null && isValidCard(targetCard)) {
                int value = handler.getBattleDieInput();
                Action.attack(targetCard, value);
            }

        }

    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;

        if(c.getOwner() != null && c.getOwner() != getHost().getOwner()
                && c.getType() == CardType.CHARACTER) {
            isValid = true;
        }

        return isValid;
    }
}
