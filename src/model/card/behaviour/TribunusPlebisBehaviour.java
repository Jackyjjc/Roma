package model.card.behaviour;

import model.ICardResources;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13/05/12
 * Time: 11:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TribunusPlebisBehaviour extends Behaviour {

    private static final int AMOUNT = 1;

    public TribunusPlebisBehaviour(AbstractCard host, ICardResources cardResources) {
        super(host, cardResources);
    }

    public void complete() {

        IPlayer owner = getHost().getOwner();
        IPlayer opponent = owner.getOpponent();

        if (isValid()) {
            Action.attainVP(opponent, owner, AMOUNT);
        }

    }

    public boolean isValid() {

        boolean isValid = false;

        if (getHost().getOwner().getOpponent().getVP() >= AMOUNT) {
            isValid = true;
        }

        return isValid;

    }
}
