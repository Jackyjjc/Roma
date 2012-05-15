package model.card.behaviour;

import model.Die;
import model.IDisc;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenturioBehaviour extends Behaviour {

    public CenturioBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        AbstractCard card = getOppositeCard();
        InputHandler handler = getHost().getGameIO().getInputHandler();
        int dieValue;

        if(isValidTarget(card)) {
            dieValue = handler.getBattleDieInput();

            boolean isAddDie = handler.getBooleanInput();
            if(isAddDie) {
                Die die = handler.getDieInput();

                if(isValidDie(die)) {
                    dieValue += die.getValue();
                }
            }
            Action.attack(card, dieValue);
        }

    }

    public boolean isValidTarget(AbstractCard target) {
        boolean isValid = false;

        if(target != null && target.getOwner() != null 
             && target.getOwner() != getHost().getOwner()) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isValidDie(Die die) {
        return !die.isUsed();
    }

    private AbstractCard getOppositeCard() {

        IPlayer opponent = getHost().getOwner().getOpponent();

        int index = getHost().getDisc().getIndex();

        IDisc disc = opponent.getField().getDisc(index);
        AbstractCard target = disc.getCard();

        return target;

    }

}
