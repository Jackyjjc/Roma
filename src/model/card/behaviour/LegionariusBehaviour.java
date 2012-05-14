package model.card.behaviour;

import model.IPlayer;
import model.card.AbstractCard;
import model.card.Action;

public class LegionariusBehaviour extends Behaviour {

    public LegionariusBehaviour(AbstractCard host) {
        super(host);
    }

    public void complete() {


        AbstractCard target = getOppositeCard();

        if(isValidCard(target)) {

            int value = getHost().getGameIO().getInputHandler().getBattleDieInput();
            Action.attack(target, value);

        }

    }

    public boolean isValidCard(AbstractCard c) {
        boolean isValid = false;
        
        if(c.getOwner() != null && c.getOwner() != this.getHost().getOwner()) {
            isValid = true;
        }
        
        return isValid;
    }


    private AbstractCard getOppositeCard() {

        IPlayer opponent = getHost().getOwner().getOpponent();

        int index = getHost().getDisc().getIndex();

        AbstractCard target = opponent.getField().getCard(index);

        return target;

    }
    
}
