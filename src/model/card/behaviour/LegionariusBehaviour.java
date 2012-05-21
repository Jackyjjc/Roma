package model.card.behaviour;

import model.IDisc;
import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;
import model.card.ICardChecker;

public class LegionariusBehaviour extends Behaviour implements ICardChecker {

    public LegionariusBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {


        AbstractCard target = getOppositeCard();

        if (target != null && isValidCard(target)) {

            int value = getHost().getCardResources().getInputHandler().getBattleDieInput();
            Action.attack(target, value);

        }

    }

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;

        if (target.getOwner() != null
                && target.getOwner() != this.getHost().getOwner()) {
            isValid = true;
        }

        return isValid;
    }


    private AbstractCard getOppositeCard() {

        IPlayer opponent = getHost().getOwner().getOpponent();

        int index = getHost().getDisc().getIndex();

        IDisc disc = opponent.getField().getDisc(index);
        AbstractCard target = disc.getCard();

        return target;

    }

}
