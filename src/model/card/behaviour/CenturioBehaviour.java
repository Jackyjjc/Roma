package model.card.behaviour;

import model.Die;
import model.IDisc;
import model.IPlayer;
import model.InputHandler;
import model.card.AbstractCard;
import model.card.Action;
import model.card.ICardChecker;
import model.card.IDieChecker;


/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenturioBehaviour extends Behaviour implements ICardChecker, IDieChecker {

    public CenturioBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {

        AbstractCard card = getOppositeCard();
        InputHandler handler = getHost().getCardResources().getInputHandler();
        int dieValue;

        if(card != null && isValidCard(card)) {
            dieValue = handler.getBattleDieInput();

            boolean isAddDie = handler.getBooleanInput();
            if(isAddDie) {
                Die die = handler.getDieInput();
                if(isValidDie(die)) {
                    die.use();
                    dieValue += die.getValue();
                }
            }
            Action.attack(card, dieValue);
        }

    }

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if(target.getOwner() != null 
             && target.getOwner() != getHost().getOwner()) {
            isValid = true;
        }

        return isValid;
    }

    public boolean isValidDie(Die die) {
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
