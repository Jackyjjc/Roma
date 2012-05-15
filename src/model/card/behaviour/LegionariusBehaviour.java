package model.card.behaviour;

import model.IDisc;
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

    public boolean isValidCard(AbstractCard target) {
        boolean isValid = false;
        
        if(target != null && target.getOwner() != null 
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
